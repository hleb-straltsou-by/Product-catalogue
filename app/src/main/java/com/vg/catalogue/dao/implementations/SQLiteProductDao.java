package com.vg.catalogue.dao.implementations;

import android.content.Context;

import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.dao.utils.ProductDbHelper;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.Product;

import java.util.List;
import java.util.UUID;

public class SQLiteProductDao implements ProductDao{

    private static SQLiteProductDao INSTANCE;

    private ProductDbHelper mDbHelper;

    private SQLiteProductDao(Context context){
        mDbHelper = ProductDbHelper.getInstance(context);
    }

    public static SQLiteProductDao getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new SQLiteProductDao(context);
        }
        return INSTANCE;
    }

    @Override
    public void addProduct(Product product, ProductCategoryEnum categoryEnum) {
        mDbHelper.insertProduct(product, categoryEnum);
    }

    @Override
    public void deleteProduct(UUID id, ProductCategoryEnum categoryEnum) {
        mDbHelper.deleteProduct(id, categoryEnum);
    }

    @Override
    public void updateProduct(UUID id, Product product, ProductCategoryEnum categoryEnum) {
        mDbHelper.updateProduct(id, product, categoryEnum);
    }

    @Override
    public Product getProduct(UUID id, ProductCategoryEnum categoryEnum) {
        return mDbHelper.getProduct(id, categoryEnum);
    }

    @Override
    public List<Product> getAllProducts(ProductCategoryEnum categoryEnum) {
        return  mDbHelper.getProducts(categoryEnum);
    }

    @Override
    public void deleteAllProducts(ProductCategoryEnum categoryEnum) {
        mDbHelper.deleteAllProducts(categoryEnum);
    }
}
