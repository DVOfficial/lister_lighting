package com.listerled.listerlighting.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.listerled.listerlighting.adaptor.ProductDao;

@Database(entities = {CartModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract ProductDao ProductDao();
}
