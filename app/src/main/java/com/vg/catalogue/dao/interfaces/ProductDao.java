package com.vg.catalogue.dao.interfaces;

import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.ActiveSubstance;
import com.vg.catalogue.model.Product;
import java.util.List;

public interface ProductDao {

    void addProduct(Product product, ProductCategoryEnum categoryEnum);

    void deleteProduct(String name, ProductCategoryEnum categoryEnum);

    List<Product> getProduct(String name, ProductCategoryEnum categoryEnum);

    List<Product> findProducts(String namePattern, ProductCategoryEnum categoryEnum);

    ActiveSubstance getActiveSubstance(long id, ProductCategoryEnum categoryEnum);

    List<Product> getAllProducts(ProductCategoryEnum categoryEnum);

    List<ActiveSubstance> getAllActiveSubstances(ProductCategoryEnum categoryEnum);

    void deleteAllProducts(ProductCategoryEnum categoryEnum);
}
