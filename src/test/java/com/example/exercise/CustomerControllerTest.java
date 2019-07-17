package com.example.exercise;

import com.example.exercise.controllers.CustomerController;
import com.example.exercise.models.Customer;
import com.example.exercise.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @Test
    public void getReturnsStatusOk() throws Exception {
        when(service.getAll()).thenReturn(Collections.singletonList(new Customer("test")));
        this.mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"test\"}]"));
    }




}
