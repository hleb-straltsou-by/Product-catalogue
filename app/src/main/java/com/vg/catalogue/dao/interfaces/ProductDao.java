package com.vg.catalogue.dao.interfaces;

import com.vg.catalogue.enums.ProductCategoryEnum;
import com.vg.catalogue.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductDao {

    void addProduct(Product product, ProductCategoryEnum categoryEnum);

    void deleteProduct(UUID id, ProductCategoryEnum categoryEnum);

    void updateProduct(UUID id, Product product, ProductCategoryEnum categoryEnum);

    Product getProduct(UUID id, ProductCategoryEnum categoryEnum);

    List<Product> getAllProducts(ProductCategoryEnum categoryEnum);

    void deleteAllProducts(ProductCategoryEnum categoryEnum);
}
