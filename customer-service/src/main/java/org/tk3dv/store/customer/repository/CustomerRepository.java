package org.tk3dv.store.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tk3dv.store.customer.repository.entity.Customer;
import org.tk3dv.store.customer.repository.entity.Region;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByNumberId(String NumberId);
    public List<Customer> findByLastName(String LastName);
    public List<Customer> findByRegion(Region region);

}
