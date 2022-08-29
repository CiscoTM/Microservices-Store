package org.tk3dv.store.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tk3dv.store.product.entity.Category;
import org.tk3dv.store.product.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product>findByCategory(Category category);

}
