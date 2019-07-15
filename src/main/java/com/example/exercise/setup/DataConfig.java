package com.example.exercise.setup;

import com.example.exercise.models.Booking;
import com.example.exercise.models.Customer;
import com.example.exercise.models.Vehicle;
import com.example.exercise.repositories.IBookingRepository;
import com.example.exercise.repositories.ICustomerRepository;
import com.example.exercise.repositories.IVehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DataConfig {

    @Bean
    public CommandLineRunner createDB(IBookingRepository bookingRepository,
                                      ICustomerRepository customerRepository,
                                      IVehicleRepository vehicleRepository){
        return (String... args) -> {

            Vehicle vehicle1 = new Vehicle(0,10000,80,"testcar1");
            Vehicle vehicle2 = new Vehicle(1,50000,120,"testcar2");

            vehicleRepository.saveAll(Arrays.asList(
                    vehicle1,
                    vehicle2
            ));

            Customer customer1 = new Customer(0,"john Doe");
            Customer customer2 = new Customer(1,"jane Doe");


            customerRepository.saveAll(Arrays.asList(
                    customer1, customer2
            ));

            Booking booking = new Booking(new Date(),new Date());
            booking.setCustomer(customer1);
            booking.setVehicle(vehicle1);

            bookingRepository.save(booking);



        };
    }

}
