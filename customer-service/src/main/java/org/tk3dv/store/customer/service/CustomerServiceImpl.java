package org.tk3dv.store.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tk3dv.store.customer.repository.CustomerRepository;
import org.tk3dv.store.customer.repository.entity.Customer;
import org.tk3dv.store.customer.repository.entity.Region;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public List<Customer> findCustomerAll() {
        return repository.findAll();
    }

    @Override
    public List<Customer> findCustomerByRegion(Region region) {
        return repository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = repository.findByNumberId(customer.getNumberId());
        if(customerDB != null){
            return customerDB;
        }
        customer.setState("CREATED");
        customerDB = repository.save(customer);
        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
//        Customer customerDB = repository.findByNumberId(customer.getNumberId());
        if(customerDB == null){
            return null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());

        return repository.save(customerDB);

    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if(customerDB == null){
            return null;
        }
        customer.setState("DELETED");
        return repository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return repository.findById(id).orElse(null);
    }
}
