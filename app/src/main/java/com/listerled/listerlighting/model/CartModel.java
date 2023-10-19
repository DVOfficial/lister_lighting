package com.listerled.listerlighting.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class CartModel {


    @ColumnInfo(name = "partyname")
    public String partyname;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "address")
    public String address;



    @ColumnInfo(name = "product_code")
    public String product_code;

    @ColumnInfo(name = "watt")
    public String watt;

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "productcode_watt")
    public String productcode_watt;

    @ColumnInfo(name = "pdttype")
    public String pdttype;

    @ColumnInfo(name = "colour")
    public String colour;

    @ColumnInfo(name = "qty")
    public int qty;

    @ColumnInfo(name = "remarks")
    public String remarks;


    public CartModel(String partyname, String city, String address, @NonNull String product_code, String watt, String productcode_watt, String pdttype, String colour, int qty, String remarks) {
        this.partyname = partyname;
        this.city = city;
        this.address = address;
        this.product_code = product_code;
        this.watt = watt;
        this.productcode_watt = productcode_watt;
        this.pdttype = pdttype;
        this.colour = colour;
        this.qty = qty;
        this.remarks = remarks;
    }


    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(@NonNull String product_code) {
        this.product_code = product_code;
    }

    public String getWatt() {
        return watt;
    }

    public void setWatt(String watt) {
        this.watt = watt;
    }

    public String getProductcode_watt() {
        return productcode_watt;
    }

    public void setProductcode_watt(String productcode_watt) {
        this.productcode_watt = productcode_watt;
    }

    public String getPdttype() {
        return pdttype;
    }

    public void setPdttype(String pdttype) {
        this.pdttype = pdttype;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
