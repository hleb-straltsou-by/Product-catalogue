package com.vg.catalogue.dao.implementations;

import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.model.Product;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmbeddedProductDaoTest {

    private ProductDao mProductDao = EmbeddedProductDao.getInstance();

    @Test
    public void getEmbeddedProductDaoInstanceTest() throws Exception {
        assertNotNull(mProductDao);
    }

    @Test
    public void addProductTest() throws Exception {
        Product product = new Product();
        product.setName("Test title");
        mProductDao.addProduct(product);
        String errorMessage = "Added product object is not correspond to retrieved moment object";
        assertEquals(errorMessage, product, mProductDao.getProduct(product.getId()));
    }

    @Test
    public void deleteExistedProductTest() throws Exception {
        Product product = new Product();
        mProductDao.addProduct(product);
        product.setName("Test title");
        mProductDao.deleteProduct(product.getId());
        String errorMessage = "After deleting product object however the same object exists in storage";
        Product resultProduct = mProductDao.getProduct(product.getId());
        assertEquals(errorMessage, resultProduct.getName(), "");
    }

    @Test
    public void updateProductTest() throws Exception {
        Product product = new Product();
        mProductDao.addProduct(product);
        product.setName("Test title");
        mProductDao.updateProduct(product.getId(), product);
        String errorMessage = "After updating product object however" +
                " doesn't correspond to updated product object";
        assertEquals(errorMessage, product, mProductDao.getProduct(product.getId()));
    }

    @Test
    public void getNotExistedProductTest() throws Exception {
        Product product = new Product();
        String errorMessage = "Expected empty object but has been retrieved actual object" +
                " after query for not existing object";
        Product resultProduct = mProductDao.getProduct(product.getId());
        assertEquals(errorMessage, resultProduct.getName(), "");
    }

    @After
    public void clearStorage(){
        mProductDao.deleteAllProducts();
    }

}