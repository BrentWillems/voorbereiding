package com.example.exercise.services;

import com.example.exercise.models.Customer;
import com.example.exercise.repositories.ICustomerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    public Optional<Customer> getCustomerById(int id){
        return customerRepository.findById(id);
    }

    public Customer update(int id, Customer upToDateCustomer) {
        return getCustomerById(id).map(customer -> {
            customer.setName(upToDateCustomer.getName());
            return customerRepository.save(customer);

        }).orElseThrow(() -> new EntityNotFoundException("CustomerID " + id + "not found"));
    }
}
