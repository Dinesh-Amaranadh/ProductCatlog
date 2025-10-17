package dev.dinesh.ecommerce.productcatlog.services;

import dev.dinesh.ecommerce.productcatlog.dto.FakeStoreProductDto;
import dev.dinesh.ecommerce.productcatlog.dto.GenericProductDto;
//import dev.dinesh.ecommerce.productcatlog.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    private  final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this .restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto getProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);

        if(response.getBody() != null) {
            FakeStoreProductDto fakeStoreProductDto = response.getBody();


            GenericProductDto product = new GenericProductDto();
            product.setId(fakeStoreProductDto.getId());
            product.setTitle(fakeStoreProductDto.getTitle());
            product.setDescription(fakeStoreProductDto.getDescription());
            product.setPrice(fakeStoreProductDto.getPrice());
            product.setCategory(fakeStoreProductDto.getCategory());
            product.setImage(fakeStoreProductDto.getImage());
            return product;
        }
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String createProductRequestUrl = "https://fakestoreapi.com/products";
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestUrl, product, GenericProductDto.class);
        if(response.getBody() != null) { return response.getBody(); }
        return null;
    }
}