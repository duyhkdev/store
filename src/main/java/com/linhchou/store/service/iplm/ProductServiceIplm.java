package com.linhchou.store.service.iplm;

import com.linhchou.store.dto.ProductDTO;
import com.linhchou.store.dto.ProductDetailDTO;
import com.linhchou.store.dto.filter.ProductFilter;
import com.linhchou.store.entity.ProductDetail;
import com.linhchou.store.entity.Product;
import com.linhchou.store.repository.ProductDetailRepository;
import com.linhchou.store.repository.ProductRepository;
import com.linhchou.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
        Page<Product> page = productRepository.getAll(filter.getKeyWord(), PageRequest.of(filter.getPageIndex(), filter.getPageSize()));
        List<ProductDTO> dtos = page.getContent().stream().map(p -> mapper.map(p, ProductDTO.class)).collect(Collectors.toList());

        dtos.forEach(p -> {
            List<ProductDetail> productDetailEntities = productDetailRepository.findByProductId(p.getId());
            List<ProductDetailDTO> productDetailDTOS = productDetailEntities.stream().map(pd -> mapper.map(pd, ProductDetailDTO.class)).collect(Collectors.toList());
            p.setDetails(productDetailDTOS);
        });

        return dtos;
    }

    @Override
    public void create(ProductDTO dto) {
        Product productEntity = mapper.map(dto, Product.class);

        productRepository.save(productEntity);
    }

    @Override
    public void update(ProductDTO dto) {

    }
}
