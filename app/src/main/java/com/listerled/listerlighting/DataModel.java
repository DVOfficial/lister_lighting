package com.listerled.listerlighting;

public class DataModel {

    private String sno, modelName,color,brand,stock,url;

    public DataModel() {
    }

    public DataModel(String sno, String modelName, String color, String brand, String stock, String url) {
        this.sno = sno;
        this.modelName = modelName;
        this.color = color;
        this.brand = brand;
        this.stock = stock;
        this.url = url;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
