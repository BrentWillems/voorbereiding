package com.example.exercise.services;

import com.example.exercise.models.Customer;
import com.example.exercise.repositories.ICustomerRepository;
import org.springframework.stereotype.Service;

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

}
