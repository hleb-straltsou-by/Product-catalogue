package com.vg.catalogue.dao.implementations;

import com.vg.catalogue.dao.interfaces.ProductDao;
import com.vg.catalogue.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmbeddedProductDao implements ProductDao {

    private static EmbeddedProductDao INSTANCE;

    private List<Product> mProducts;

    private final static int INITIAL_PRODUCT_LIST_SIZE = 20;

    private EmbeddedProductDao(){
        mProducts = new ArrayList<>(INITIAL_PRODUCT_LIST_SIZE);
        Product product;
        for(int i = 0; i < INITIAL_PRODUCT_LIST_SIZE; i++){
            product = new Product();
            product.setName("Title #" + i);
            mProducts.add(product);
        }
    }

    public static EmbeddedProductDao getInstance(){
        if(INSTANCE == null){
            INSTANCE = new EmbeddedProductDao();
        }
        return INSTANCE;
    }

    @Override
    public void addProduct(Product product) {
        mProducts.add(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        for(int i = 0; i < mProducts.size(); i++){
            if(mProducts.get(i).getId().equals(id)){
                mProducts.remove(i);
            }
        }
    }

    @Override
    public void updateProduct(UUID id, Product product) {
        for(int i = 0; i < mProducts.size(); i++){
            if(mProducts.get(i).getId().equals(id)){
                mProducts.get(i).setName(product.getName());
                mProducts.get(i).setAllNames(product.getAllNames());
                mProducts.get(i).setConsumptionRate(product.getConsumptionRate());
                mProducts.get(i).setCategory(product.getCategory());
                mProducts.get(i).setDaysTillLastHarvest(product.getDaysTillLastHarvest());
                mProducts.get(i).setHarmfulOrganismOrDisease(product.getHarmfulOrganismOrDisease());
                mProducts.get(i).setOperatingPrinciple(product.getOperatingPrinciple());
                mProducts.get(i).setProcessedCultures(product.getProcessedCultures());
                mProducts.get(i).setTreatmentsMultiplicity(product.getTreatmentsMultiplicity());
            }
        }
    }

    @Override
    public void deleteAllProducts() {
        mProducts.clear();
    }

    @Override
    public Product getProduct(UUID id) {
        for(int i = 0; i < mProducts.size(); i++){
            if(mProducts.get(i).getId().equals(id)){
                return mProducts.get(i);
            }
        }
        return getEmptyProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return mProducts;
    }

    private Product getEmptyProduct(){
        return new Product();
    }
}
