package com.linhchou.store.service.iplm;

import com.linhchou.store.dto.ProductDTO;
import com.linhchou.store.dto.ProductDetailDTO;
import com.linhchou.store.dto.filter.ProductFilter;
import com.linhchou.store.entity.ProductDetailEntity;
import com.linhchou.store.entity.ProductEntity;
import com.linhchou.store.repository.ProductDetailRepository;
import com.linhchou.store.repository.ProductRepository;
import com.linhchou.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceIplm implements ProductService {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDTO> getAll(ProductFilter filter) {
        Page<ProductEntity> page = productRepository.getAll(filter.getKeyWord(), PageRequest.of(filter.getPageIndex(), filter.getPageSize()));
        List<ProductDTO> dtos = page.getContent().stream().map(p -> mapper.map(p, ProductDTO.class)).collect(Collectors.toList());

        dtos.forEach(p -> {
            List<ProductDetailEntity> productDetailEntities = productDetailRepository.findByProductId(p.getId());
            List<ProductDetailDTO> productDetailDTOS = productDetailEntities.stream().map(pd -> mapper.map(pd, ProductDetailDTO.class)).collect(Collectors.toList());
            p.setDetails(productDetailDTOS);
        });

        return dtos;
    }
}
