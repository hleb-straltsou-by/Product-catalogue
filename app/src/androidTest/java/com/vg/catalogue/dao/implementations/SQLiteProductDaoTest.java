package com.vg.catalogue.dao.implementations;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.Product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SQLiteProductDaoTest {

    private Context appContext;

    private ProductDao mProductDao;

    public SQLiteProductDaoTest(){
        appContext = InstrumentationRegistry.getTargetContext();
        mProductDao = SQLiteProductDao.getInstance(appContext);
    }

    @Before
    public void deleteAllProductsFromHerbicidesTest() throws Exception {
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        String errorMessage = "Expected size of result list equal to 0";

        mProductDao.deleteAllProducts(categoryEnum);
        assertEquals(errorMessage, 0, mProductDao.getAllProducts(categoryEnum).size());
    }

    @Test
    public void verifyAppContextTest() throws Exception {
        String errorMessage = "Android app context is not initialized";
        assertNotNull(errorMessage , appContext);
    }

    @Test
    public void getInstanceTest() throws Exception {
        String errorMessage = "SQLiteProductDao is not initialized";
        assertNotNull(errorMessage, mProductDao);
    }

    @Test
    public void getSQLiteProductDaoInstanceTest() throws Exception {
        assertNotNull(mProductDao);
    }

    @Test
    public void addProductToHerbicidesTest() throws Exception {
        Product product = new Product();
        product.setName("Test name");
        product.setActiveSubstanceId(1);

        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        mProductDao.addProduct(product, categoryEnum);

        String errorMessage = "Added product object is not correspond to retrieved moment object";
        assertEquals(errorMessage, product, mProductDao.getProduct(product.getId(), categoryEnum));
    }

    @Test
    public void deleteExistedProductFromHerbicidesTest() throws Exception {
        Product product = new Product();
        product.setActiveSubstanceId(1);
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        mProductDao.addProduct(product, categoryEnum);
        product.setName("Test name");

        mProductDao.deleteProduct(product.getId(), categoryEnum);
        String errorMessage = "After deleting product object however the same object exists in storage";

        Product resultProduct = mProductDao.getProduct(product.getId(), categoryEnum);
        assertEquals(errorMessage, resultProduct.getName(), "");
    }

    @Test
    public void updateProductInHerbicidesTest() throws Exception {
        Product product = new Product();
        product.setActiveSubstanceId(1);
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        mProductDao.addProduct(product, categoryEnum);
        product.setName("Test name");

        mProductDao.updateProduct(product.getId(), product, categoryEnum);

        String errorMessage = "After updating product object however" +
                " doesn't correspond to updated product object";
        assertEquals(errorMessage, product, mProductDao.getProduct(product.getId(), categoryEnum));
    }

    @Test
    public void getNotExistedProductFromHerbicidesTest() throws Exception {
        Product product = new Product();
        product.setActiveSubstanceId(1);
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        String errorMessage = "Expected empty object but has been retrieved actual object" +
                " after query for not existing object";
        Product resultProduct = mProductDao.getProduct(product.getId(), categoryEnum);
        assertEquals(errorMessage, resultProduct.getName(), "");
    }
}