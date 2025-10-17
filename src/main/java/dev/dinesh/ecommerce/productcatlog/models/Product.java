package dev.dinesh.ecommerce.productcatlog.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends  BaseModel{

    private  String title;

    private  String description;

    private  double price;

    private Category category;

    private  String image;
}
