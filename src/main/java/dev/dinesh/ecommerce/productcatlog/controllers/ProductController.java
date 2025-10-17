package dev.dinesh.ecommerce.productcatlog.controllers;


import dev.dinesh.ecommerce.productcatlog.dto.GenericProductDto;
import dev.dinesh.ecommerce.productcatlog.models.Product;
import dev.dinesh.ecommerce.productcatlog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    //@Autowired
    private ProductService productService;

    public   ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getAllProducts(){
        return "Products List";
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return  productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct( @RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("{id}")
    public  String deleteProductById(@PathVariable("id") Long id){
        return "Product deleted with id "+ id;
    }

    @PutMapping("{id}")
    public  String updateProductById(@PathVariable("id") Long id){

        return "Product updated with id "+ id;
    }
}
