package com.example.exercise.services;

import com.example.exercise.exceptions.BookingNotAvailableException;
import com.example.exercise.models.Booking;
import com.example.exercise.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking){
        if(bookingPeriodAvailable(booking)){
            bookingRepository.save(booking);
        }else{
            throw new BookingNotAvailableException("Booking " + booking.getId() + " not available: " + booking.getFrom() + " | " + booking.getTo());
        }

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

    public boolean bookingPeriodAvailable(Booking booking){
        return this.getBookingsForVehicle(booking.getVehicle().getId()).stream()
                .filter((b) -> b.getFrom().getTime() <= booking.getTo().getTime()
                        && b.getTo().getTime() >= booking.getFrom().getTime()).count() == 0l;
    }
}
