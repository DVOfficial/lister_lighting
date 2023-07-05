package com.listerled.listerlighting;

import androidx.annotation.Keep;

@Keep
public class Class_ListerData {

    public String Sno;
    public String ModelName;
    public String COLOR;
    public String BRAND;


    public String OPENINGSTOCK;
    public String INQTY;
    public String Out;
    public String CURRENTSTOCK;

    public String Location;
    public String ImageLink;
    public String Date;

    public Class_ListerData() {
    }

    public Class_ListerData(String sno, String modelName, String COLOR, String BRAND, String OPENINGSTOCK, String INQTY, String out, String CURRENTSTOCK, String location, String imageLink, String date) {
        Sno = sno;
        ModelName = modelName;
        this.COLOR = COLOR;
        this.BRAND = BRAND;
        this.OPENINGSTOCK = OPENINGSTOCK;
        this.INQTY = INQTY;
        Out = out;
        this.CURRENTSTOCK = CURRENTSTOCK;
        Location = location;
        ImageLink = imageLink;
        Date = date;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }

    public String getOPENINGSTOCK() {
        return OPENINGSTOCK;
    }

    public void setOPENINGSTOCK(String OPENINGSTOCK) {
        this.OPENINGSTOCK = OPENINGSTOCK;
    }

    public String getINQTY() {
        return INQTY;
    }

    public void setINQTY(String INQTY) {
        this.INQTY = INQTY;
    }

    public String getOut() {
        return Out;
    }

    public void setOut(String out) {
        Out = out;
    }

    public String getCURRENTSTOCK() {
        return CURRENTSTOCK;
    }

    public void setCURRENTSTOCK(String CURRENTSTOCK) {
        this.CURRENTSTOCK = CURRENTSTOCK;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
