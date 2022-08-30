package org.tk3dv.store.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tk3dv.store.shopping.model.Customer;

@FeignClient(name = "customer-service", path = "localhost:8092")
//@RequestMapping("/customers")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);
}
