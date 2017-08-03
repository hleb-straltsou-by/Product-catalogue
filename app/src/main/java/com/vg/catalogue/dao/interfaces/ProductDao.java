package com.vg.catalogue.dao.interfaces;

import com.vg.catalogue.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductDao {

    void addProduct(Product product);

    void deleteProduct(UUID id);

    void updateProduct(UUID id, Product product);

    Product getProduct(UUID id);

    List<Product> getAllProducts();

    void deleteAllProducts();
}
