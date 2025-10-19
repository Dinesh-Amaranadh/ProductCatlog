package dev.dinesh.ecommerce.productcatlog.services;

import dev.dinesh.ecommerce.productcatlog.dto.FakeStoreProductDto;
import dev.dinesh.ecommerce.productcatlog.dto.GenericProductDto;
import org.apache.catalina.users.GenericRole;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class FakeStoreProductService implements ProductService{
    private  final RestTemplateBuilder restTemplateBuilder;

    private String productRequestBaseUrl ="https://fakestoreapi.com/products" ;
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this .restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreProductToGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }

    @Override
    public GenericProductDto getProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();


        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl, product, FakeStoreProductDto.class);
        return convertFakeStoreProductToGenericProduct(response.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();


        GenericProductDto currentProduct = restTemplate.getForObject(specificProductRequestUrl, GenericProductDto.class,id);


            //currentProduct.setId(product.getId());
            currentProduct.setTitle(product.getTitle());
            currentProduct.setDescription(product.getDescription());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setCategory(product.getCategory());
            currentProduct.setImage(product.getImage());

            restTemplate.put(specificProductRequestUrl,currentProduct,id);
            return currentProduct;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
         return   convertFakeStoreProductToGenericProduct(fakeStoreProductDto);

    }

    @Override
    public ArrayList<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        ArrayList<GenericProductDto> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){
            products.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDto));
        }
        return   products;

    }
}