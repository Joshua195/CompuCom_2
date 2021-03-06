package com.example.CompuCom2.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductModel {
    private Integer id;
    private String name;
    private String category;
    private Double price;
    private MultipartFile image;
    private String description;
    private DiscountModel discount;
    private ProductQuantityModel productQuantityModel;

    public ProductModel(){}

    public ProductModel(Integer id, String name, String category, Double price, MultipartFile image, String description, DiscountModel discount, ProductQuantityModel productQuantityModel) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.description = description;
        this.discount = discount;
        this.productQuantityModel = productQuantityModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiscountModel getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountModel discount) {
        this.discount = discount;
    }

    public ProductQuantityModel getProductQuantityModel() {
        return productQuantityModel;
    }

    public void setProductQuantityModel(ProductQuantityModel productQuantityModel) {
        this.productQuantityModel = productQuantityModel;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", productQuantityModel=" + productQuantityModel +
                '}';
    }
}
