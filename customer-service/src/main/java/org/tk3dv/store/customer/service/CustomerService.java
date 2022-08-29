package org.tk3dv.store.customer.service;


import org.tk3dv.store.customer.repository.entity.Customer;
import org.tk3dv.store.customer.repository.entity.Region;

import java.util.List;

public interface CustomerService {

    public List<Customer>findCustomerAll();
    public List<Customer>findCustomerByRegion(Region region);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public Customer getCustomer(Long id);



}
