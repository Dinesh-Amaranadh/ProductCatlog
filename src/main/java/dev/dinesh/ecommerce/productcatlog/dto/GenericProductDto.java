package dev.dinesh.ecommerce.productcatlog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDto {

    private Long id;

    private String title;

    private String description;

    private String image;

    private String category;

    private double price;
}
