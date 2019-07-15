package com.example.exercise.controllers;

import com.example.exercise.models.Customer;
import com.example.exercise.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/id")
    public ResponseEntity getCustomerById(@PathVariable("id") int id){
        Optional<Customer> customer = customerService.getCustomerById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity addCustomer(Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.ok(customer);
    }
}