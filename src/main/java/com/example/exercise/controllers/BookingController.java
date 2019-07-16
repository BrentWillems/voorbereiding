package com.example.exercise.controllers;

import com.example.exercise.models.Booking;
import com.example.exercise.services.BookingService;
import com.example.exercise.services.CustomerService;
import com.example.exercise.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
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

        long ammountOfOverlappingBookingsForCar = bookingService.getBookingsForVehicle(booking.getVehicle().getId()).stream()
                .filter((b) -> b.getFrom().getTime() <= booking.getTo().getTime()
                        && b.getTo().getTime() >= booking.getFrom().getTime()).count();

        if(ammountOfOverlappingBookingsForCar==0l){
            bookingService.addBooking(booking);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Car is already in use for the selected period.");
        }

        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBooking(@PathVariable int id, @RequestBody Booking booking){

        bookingService.update(id,booking);
        return ResponseEntity.noContent().build();
    }

}
