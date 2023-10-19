package com.listerled.listerlighting.adaptor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.listerled.listerlighting.model.CartModel;

import java.util.List;

@Dao
public interface ProductDao
{
    @Insert
    void insertrecord(CartModel cartModel);


    @Query("SELECT EXISTS(SELECT * FROM CartModel WHERE productcode_watt = :productid_pwatt)")
    Boolean is_exist(String productid_pwatt);


    @Query("SELECT * FROM CartModel")
    List<CartModel> getallproduct();

    @Query("DELETE FROM CartModel WHERE productcode_watt = :id")
    void deleteById(String id);

    @Query("DELETE FROM CartModel")
    void deleteCart();

}
