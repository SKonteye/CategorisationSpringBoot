package com.isi.categorisationproject.model;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class })
public interface ProductMapper {

    // Mapping methods
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO dto);

    // Method to update an existing Product entity from a DTO
    void updateProductFromDTO(ProductDTO dto, @MappingTarget Product entity);
}
