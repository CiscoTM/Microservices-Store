package org.tk3dv.store.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tk3dv.store.shopping.model.Product;

@FeignClient(name = "product-service", path = "http://localhost:8091")
//@RequestMapping(value = "/products")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);
    @GetMapping(value = "/products/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,@RequestParam(name = "quantity",required = true) Double quantity);

    }
