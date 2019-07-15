package com.example.exercise.controllers;

import com.example.exercise.models.Booking;
import com.example.exercise.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity getAllBookings(){
        List<Booking> bookings = bookingService.getAll();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/id")
    public ResponseEntity getBookingById(@PathVariable("id") int id){
        Optional<Booking> booking = bookingService.getBookingById(id);
        if(booking.isPresent()){
            return ResponseEntity.ok(booking.get());
        }
        return ResponseEntity.notFound().build();
    }

}
