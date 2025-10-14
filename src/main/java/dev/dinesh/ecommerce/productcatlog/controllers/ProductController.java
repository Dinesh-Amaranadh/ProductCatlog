package dev.dinesh.ecommerce.productcatlog.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public String getAllProducts(){
        return "Products List";
    }

    @GetMapping("{id}")
    public  String getProductById(@PathVariable("id") Long id){
        return "Product with id " + id;
    }

    @PostMapping
    public String createProduct() {
        return "Product Created with id "+ UUID.randomUUID();
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
