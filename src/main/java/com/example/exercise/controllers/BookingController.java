package com.example.exercise.controllers;

import com.example.exercise.models.Booking;
import com.example.exercise.services.BookingService;
import com.example.exercise.services.CustomerService;
import com.example.exercise.services.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    private BookingService bookingService;
    private CustomerService customerService;
    private VehicleService vehicleService;

    public BookingController(BookingService bookingService, CustomerService customerService, VehicleService vehicleService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity getAllBookings(){
        List<Booking> bookings = bookingService.getAll();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookingById(@PathVariable("id") int id){
        Optional<Booking> booking = bookingService.getBookingById(id);
        if(booking.isPresent()){
            return ResponseEntity.ok(booking.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity addBooking(@RequestBody Booking booking){
        boolean customerExists = customerService.getCustomerById(booking.getCustomer().getId()).isPresent();
        boolean vehicleExists = vehicleService.getVehicleById(booking.getVehicle().getId()).isPresent();
        if(!customerExists) {
            customerService.addCustomer(booking.getCustomer());
        }
        if(!vehicleExists){
            vehicleService.addVehicle(booking.getVehicle());
        }

        bookingService.addBooking(booking);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBooking(@PathVariable int id, @RequestBody Booking booking){

        bookingService.update(id,booking);
        return ResponseEntity.noContent().build();
    }

}
