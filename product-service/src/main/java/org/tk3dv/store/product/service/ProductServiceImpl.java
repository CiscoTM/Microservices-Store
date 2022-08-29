package org.tk3dv.store.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tk3dv.store.product.entity.Category;
import org.tk3dv.store.product.entity.Product;
import org.tk3dv.store.product.repository.ProductRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    @Override
    public List<Product> listAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {

        product.setStatus("CREATED");
        product.setCreateAt(new Date());

        return repository.save(product);
    }

    @Override
    public Product UpdateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if(productDB == null){
            return null;
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return repository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if(productDB == null){
            return null;
        }
        productDB.setStatus("DELETED");

        return repository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public Product UpdateStock(Long id, Double quantity) {

        Product productDB = getProduct(id);
        if(productDB == null){
            return null;
        }

        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);

        return repository.save(productDB);
    }
}
