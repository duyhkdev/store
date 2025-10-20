package com.linhchou.store.service.iplm;

import com.linhchou.store.dto.BillDTO;
import com.linhchou.store.dto.BillDetailDTO;
import com.linhchou.store.dto.ProductDTO;
import com.linhchou.store.entity.Bill;
import com.linhchou.store.entity.BillDetail;
import com.linhchou.store.entity.FeeFormula;
import com.linhchou.store.enums.FeeType;
import com.linhchou.store.repository.BillDetailRepository;
import com.linhchou.store.repository.BillRepository;
import com.linhchou.store.repository.FeeFormulaRepository;
import com.linhchou.store.service.BillService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillServiceIplm implements BillService {

    private final BillRepository billRepository;
    private final BillDetailRepository billDetailRepository;
    private final FeeFormulaRepository feeFormulaRepository;
    private final ModelMapper mapper;

    @Override
    public void create(BillDTO dto) {
        calculateInterest(dto.getDetails(), dto.getAdsCosts());
    }

    @Override
    public void update(BillDTO dto) {
        calculateInterest(dto.getDetails(), dto.getAdsCosts());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        billDetailRepository.deleteByBillId(id);
        billRepository.deleteById(id);
    }

    @Override
    public BillDTO getById(Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        List<BillDetail> billDetails = billDetailRepository.findByBillId(id);
        BillDTO dto = mapper.map(bill, BillDTO.class);
        List<BillDetailDTO> billDetailDTOS = billDetails.stream().map(dt -> mapper.map(dt, BillDetailDTO.class)).collect(Collectors.toList())
        dto.setDetails(billDetailDTOS);
        return dto;
    }

    private void calculateInterest(List<BillDetailDTO> details, Double adsCosts) {
        details.forEach(dt -> {
            List<FeeFormula> feeFormulas = feeFormulaRepository.findByPlatform(dt.getPlatForm());
            ProductDTO productDTO = dt.getProduct();
            Double feePercent = feeFormulas.stream().filter(f -> FeeType.PERCENT.name().equals(f.getFeeType()))
                    .mapToDouble(f -> f.getValue()).sum();
            Double feeAmount = feeFormulas.stream().filter(f -> FeeType.AMOUNT.name().equals(f.getFeeType()))
                    .mapToDouble(f -> f.getValue()).sum();
            Double interestAmount = (productDTO.getPriceSell() * feePercent) - (productDTO.getPriceImport() + feeAmount);
            dt.setInterestAmount(interestAmount);
        });
        Double sumInterestAmount = details.stream()
                .mapToDouble(BillDetailDTO::getInterestAmount).sum();
        Bill bill = Bill.builder()
                .createdTime(new Date())
                .adsCosts(adsCosts)
                .sumInterestAmount(sumInterestAmount)
                .build();
        billRepository.save(bill);
        List<BillDetail> detailEntities = details.stream()
                .map(dt -> mapper.map(dt, BillDetail.class))
                .peek(dt -> dt.setBill(bill))
                .collect(Collectors.toList());
        billDetailRepository.saveAll(detailEntities);
    }
}
