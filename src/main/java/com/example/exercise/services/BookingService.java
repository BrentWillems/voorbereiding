package com.example.exercise.services;

import com.example.exercise.models.Booking;
import com.example.exercise.repositories.IBookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private IBookingRepository bookingRepository;

    public BookingService(IBookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking){
        bookingRepository.save(booking);
        return booking;
    }

    public Optional<Booking> getBookingById(int id){
        return bookingRepository.findById(id);
    }
}
