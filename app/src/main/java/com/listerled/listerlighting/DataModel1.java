package com.listerled.listerlighting;

public class DataModel1 {

    private String sno, productCode,category, series,model,url,hotSelling,upcoming,offer;

    public DataModel1() {
    }

    public DataModel1(String sno, String productCode, String category, String series, String model, String url, String hotSelling, String upcoming, String offer) {
        this.sno = sno;
        this.productCode = productCode;
        this.category = category;
        this.series = series;
        this.model = model;
        this.url = url;
        this.hotSelling = hotSelling;
        this.upcoming = upcoming;
        this.offer = offer;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHotSelling() {
        return hotSelling;
    }

    public void setHotSelling(String hotSelling) {
        this.hotSelling = hotSelling;
    }

    public String getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(String upcoming) {
        this.upcoming = upcoming;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
