package com.example.exercise;

import com.example.exercise.controllers.BookingController;
import com.example.exercise.exceptions.BookingNotAvailableException;
import com.example.exercise.models.Booking;
import com.example.exercise.models.Customer;
import com.example.exercise.models.Vehicle;
import com.example.exercise.services.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void returnsErrorIfBookingNotAvailable() throws Exception {

        Booking booking = new Booking(Date.from(Instant.now()), Date.from(Instant.now()));
        booking.setVehicle(new Vehicle(10,100,"test"));
        booking.setCustomer(new Customer("John Doe"));

        when(bookingService.addBooking(booking)).thenThrow(BookingNotAvailableException.class);

        this.mockMvc.perform(post("/booking")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isInternalServerError());
    }

}
