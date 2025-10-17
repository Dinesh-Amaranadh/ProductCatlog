package dev.dinesh.ecommerce.productcatlog.services;

import dev.dinesh.ecommerce.productcatlog.dto.GenericProductDto;



public interface ProductService {
    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto product);
}
