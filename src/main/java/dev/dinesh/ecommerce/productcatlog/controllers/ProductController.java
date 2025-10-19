package dev.dinesh.ecommerce.productcatlog.controllers;


import dev.dinesh.ecommerce.productcatlog.dto.GenericProductDto;
//import dev.dinesh.ecommerce.productcatlog.models.Product;
import dev.dinesh.ecommerce.productcatlog.services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/products")
public class ProductController {

    //@Autowired
    private final ProductService productService;

    public   ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ArrayList<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
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
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id){

        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public  GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product) {

        return productService.updateProductById(id,product);
    }
}
