package com.vg.catalogue.dao.implementations;

import android.content.Context;

import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.dao.utils.ProductDbHelper;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.ActiveSubstance;
import com.vg.catalogue.model.Product;

import java.util.List;

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
    public void deleteProduct(String name, ProductCategoryEnum categoryEnum) {
        mDbHelper.deleteProduct(name, categoryEnum);
    }

    @Override
    public List<Product> getProduct(String name, ProductCategoryEnum categoryEnum) {
        return mDbHelper.getProduct(name, categoryEnum);
    }

    @Override
    public List<Product> findProducts(String namePattern, ProductCategoryEnum categoryEnum) {
        return mDbHelper.findProducts(preparePattern(namePattern), categoryEnum);
    }

    @Override
    public List<Product> findProducts(String culture, String harmfulOrganism, String allNames,
                                      String activeSubstanceName, ProductCategoryEnum categoryEnum) {
        return mDbHelper.findProducts(
                preparePattern(culture),
                preparePattern(harmfulOrganism),
                preparePattern(allNames),
                preparePattern(activeSubstanceName),
                categoryEnum);
    }

    @Override
    public ActiveSubstance getActiveSubstance(long id, ProductCategoryEnum categoryEnum) {
        return mDbHelper.getActiveSubstance(id, categoryEnum);
    }

    @Override
    public int[] findActiveSubstanceIdsByName(String name, ProductCategoryEnum categoryEnum){
        return mDbHelper.findActiveSubstanceIdsByName(preparePattern(name), categoryEnum);
    }


    @Override
    public List<Product> getAllProducts(ProductCategoryEnum categoryEnum) {
        return  mDbHelper.getProducts(categoryEnum);
    }

    @Override
    public List<ActiveSubstance> getAllActiveSubstances(ProductCategoryEnum categoryEnum) {
        return mDbHelper.getActiveSubstances(categoryEnum);
    }

    @Override
    public void deleteAllProducts(ProductCategoryEnum categoryEnum) {
        mDbHelper.deleteAllProducts(categoryEnum);
    }

    private String preparePattern(String pattern){
        if(pattern == null){
            pattern = "%";
        }
        else if(pattern.equals("")){
            pattern = "%";
        } else {
            pattern = "%" + pattern + "%";
        }
        return pattern;
    }
}
