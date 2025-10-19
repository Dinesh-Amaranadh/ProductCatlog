package dev.dinesh.ecommerce.productcatlog.services;

import dev.dinesh.ecommerce.productcatlog.dto.GenericProductDto;

import java.util.ArrayList;


public interface ProductService {
    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto updateProductById(Long id, GenericProductDto product);
    GenericProductDto deleteProductById(Long id);
    ArrayList<GenericProductDto> getAllProducts();
}
