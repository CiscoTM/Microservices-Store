package org.tk3dv.store.product.service;

import org.tk3dv.store.product.entity.*;
import java.util.List;

public interface ProductService {

    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product UpdateProduct(Product product);
    public Product deleteProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product UpdateStock(Long id, Double quantity);
}
