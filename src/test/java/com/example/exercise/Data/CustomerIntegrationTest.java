package com.example.exercise.Data;

import com.example.exercise.models.Customer;
import com.example.exercise.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindAllThenReturnCustomers(){

        Customer customer = new Customer("John Doe");
        entityManager.persist(customer);
        entityManager.flush();

        List<Customer> customers = customerRepository.findAll();

        assertThat(customers.size()).isEqualTo(1);
        assertThat(customers.get(0).getName()).isEqualTo("John Doe");

    }


}
