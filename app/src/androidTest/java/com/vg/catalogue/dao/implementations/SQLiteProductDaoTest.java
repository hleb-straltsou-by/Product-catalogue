package com.vg.catalogue.dao.implementations;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.ActiveSubstance;
import com.vg.catalogue.model.Product;

import org.junit.After;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class SQLiteProductDaoTest {

    private Context appContext;

    private ProductDao mProductDao;

    public SQLiteProductDaoTest(){
        appContext = InstrumentationRegistry.getTargetContext();
        mProductDao = SQLiteProductDao.getInstance(appContext);
    }

    @After
    public void deleteIllegalProducts(){
        mProductDao.deleteProduct("", ProductCategoryEnum.HERBICIDES);
    }

    @Test
    public void verifyAppContextTest() throws Exception {
        String errorMessage = "Android app context is not initialized";
        assertNotNull(errorMessage , appContext);

        List<Product> products = mProductDao.getAllProducts(
                ProductCategoryEnum.HERBICIDES);
        System.out.println(products.size());
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
        product.setName("test name");
        product.setActiveSubstanceId(1);

        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        mProductDao.addProduct(product, categoryEnum);

        String errorMessage = "Added product object is not correspond to retrieved moment object";
        assertEquals(errorMessage, product.getName(),
                mProductDao.getProduct(product.getName(), categoryEnum).get(0).getName());
        mProductDao.deleteProduct(product.getName(), categoryEnum);
    }

    @Test
    public void deleteExistedProductFromHerbicidesTest() throws Exception {
        Product product = new Product();
        product.setActiveSubstanceId(1);
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        mProductDao.addProduct(product, categoryEnum);
        product.setName("test name");

        mProductDao.deleteProduct(product.getName(), categoryEnum);
        String errorMessage = "After deleting product object however the same object exists in storage";

        Product resultProduct = mProductDao.getProduct(product.getName(), categoryEnum).get(0);
        assertEquals(errorMessage, resultProduct.getName(), "");
    }

    @Test
    public void getNotExistedProductFromHerbicidesTest() throws Exception {
        Product product = new Product();
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;

        String errorMessage = "Expected empty object but has been retrieved actual object" +
                " after query for not existing object";
        Product resultProduct = mProductDao.getProduct(product.getName(), categoryEnum).get(0);
        assertEquals(errorMessage, resultProduct.getName(), "");
    }

    @Test
    public void findExistedProductsFromHerbicidesTest() throws Exception {
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;
        String testName1 = "xxx";
        String testName2 = "xxxx";
        String testName3 = "xxxxx";
        String testName4 = "xxtx";
        String testName5 = "xx x";

        Product product = new Product();
        product.setName(testName1);
        mProductDao.addProduct(product, categoryEnum);
        product.setName(testName2);
        mProductDao.addProduct(product, categoryEnum);
        product.setName(testName3);
        mProductDao.addProduct(product, categoryEnum);
        product.setName(testName4);
        mProductDao.addProduct(product, categoryEnum);
        product.setName(testName5);
        mProductDao.addProduct(product, categoryEnum);

        String errorMessage = "Size of result list with products doesn't correspond to expected";
        String namePattern = "xxx";
        int expectedListSize = 3;
        List<Product> resultList = mProductDao.findProducts(namePattern, categoryEnum);
        assertEquals(errorMessage, expectedListSize, resultList.size());

        // remove added products
        mProductDao.deleteProduct(testName1, categoryEnum);
        mProductDao.deleteProduct(testName2, categoryEnum);
        mProductDao.deleteProduct(testName3, categoryEnum);
        mProductDao.deleteProduct(testName4, categoryEnum);
        mProductDao.deleteProduct(testName5, categoryEnum);
    }

    @Test
    public void getAllHerbicidesActiveSubstancesTest() throws Exception {
        ProductCategoryEnum category = ProductCategoryEnum.HERBICIDES;

        String errorMessage = "Size of result list with active substances doesn't correspond to expected";
        List<ActiveSubstance> substances = mProductDao.getAllActiveSubstances(category);
        int expectedSizeOfList = 115;

        assertEquals(errorMessage, expectedSizeOfList, substances.size());
    }

    @Test
    public void getAllHerbicidesTest() throws Exception {
        String errorMessage = "Size of result list with products doesn't correspond to expected";
        List<Product> products = mProductDao.getAllProducts(
                ProductCategoryEnum.HERBICIDES);
        int expectedSizeOfList = 25;
        assertEquals(errorMessage, expectedSizeOfList, products.size());
    }

    @Test
    public void findExistedProductsFromHerbicidesComplexQueryTest() throws Exception {
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;
        String culture = "пше";
        String harmfulOrganism = "одно";
        String allNames = "дрот";
        String activeSubstanceId = "2-эгэ";

        String errorMessage = "Size of result list with products doesn't correspond to expected";
        int expectedListSize = 6;
        List<Product> resultList = mProductDao.findProducts(culture, harmfulOrganism,
                allNames, activeSubstanceId, categoryEnum);
        assertEquals(errorMessage, expectedListSize, resultList.size());

        List<Product> products = mProductDao.getAllProducts(
                ProductCategoryEnum.HERBICIDES);
        System.out.println(products.size());
    }

    @Test
    public void findExistedProductsFromHerbicidesComplexQueryTestWithNoActionSubstance() throws Exception {
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;
        String culture = "пше";
        String harmfulOrganism = "одно";
        String allNames = "дрот";
        String activeSubstanceId = null;

        String errorMessage = "Size of result list with products doesn't correspond to expected";
        int expectedListSize = 230;
        List<Product> resultList = mProductDao.findProducts(culture, harmfulOrganism,
                allNames, activeSubstanceId, categoryEnum);
        assertEquals(errorMessage, expectedListSize, resultList.size());

        List<Product> products = mProductDao.getAllProducts(
                ProductCategoryEnum.HERBICIDES);
        System.out.println(products.size());
    }

    @Test
    public void findExistedProductsFromHerbicidesTestWithTest() throws Exception {
        ProductCategoryEnum categoryEnum = ProductCategoryEnum.HERBICIDES;
        String culture = "пше";
        String harmfulOrganism = "";
        String allNames = "";
        String activeSubstanceId = null;

        String errorMessage = "Size of result list with products doesn't correspond to expected";
        int expectedListSize = 1265;
        List<Product> resultList = mProductDao.findProducts(culture, harmfulOrganism,
                allNames, activeSubstanceId, categoryEnum);
        assertEquals(errorMessage, expectedListSize, resultList.size());

        List<Product> products = mProductDao.getAllProducts(
                ProductCategoryEnum.HERBICIDES);
        System.out.println(products.size());
    }

    @Test
    public void findActiveSubstancesIdsByNameTest() throws Exception {
        String namePattern = "2-эгэ";
        ProductCategoryEnum category = ProductCategoryEnum.HERBICIDES;

        int[] resultIds = mProductDao.findActiveSubstanceIdsByName(namePattern, category);
        String errorMessage = "Size of result array with products doesn't correspond to expected";
        int expectedArraySize = 3;
        assertEquals(errorMessage, expectedArraySize, resultIds.length);
    }


}