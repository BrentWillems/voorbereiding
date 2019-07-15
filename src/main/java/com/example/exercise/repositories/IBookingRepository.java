package com.example.exercise.repositories;

import com.example.exercise.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {
}
