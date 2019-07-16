package com.example.exercise.services;

import com.example.exercise.models.Booking;
import com.example.exercise.repositories.IBookingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Booking> getBookingsForVehicle(int id){
        return bookingRepository.findAll().stream()
                .filter((booking -> booking.getVehicle().getId()==id))
                .collect(Collectors.toList());
    }

    public Booking update(int id, Booking upToDateBooking) {
        return getBookingById(id).map(booking -> {
            booking.setCustomer(upToDateBooking.getCustomer());
            booking.setVehicle(upToDateBooking.getVehicle());
            booking.setTo(upToDateBooking.getTo());
            booking.setFrom(upToDateBooking.getFrom());
            return bookingRepository.save(booking);

        }).orElseThrow(() -> new EntityNotFoundException("BookingID " + id + "not found"));
    }
}
