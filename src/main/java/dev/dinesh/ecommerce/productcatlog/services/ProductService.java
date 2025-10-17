package dev.dinesh.ecommerce.productcatlog.services;

import dev.dinesh.ecommerce.productcatlog.dto.GenericProductDto;
import dev.dinesh.ecommerce.productcatlog.models.Product;
import org.springframework.stereotype.Service;


public interface ProductService {
    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto product);
}
