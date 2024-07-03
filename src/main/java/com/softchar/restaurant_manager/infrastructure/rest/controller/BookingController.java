package com.softchar.restaurant_manager.infrastructure.rest.controller;

import com.softchar.restaurant_manager.application.BookingServiceImpl;
import com.softchar.restaurant_manager.application.TableServiceImpl;
import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.dto.ReservationRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingServiceImpl bookingServiceImpl;
    private final TableServiceImpl tableServiceImpl;

    public BookingController(BookingServiceImpl bookingServiceImpl, TableServiceImpl tableServiceImpl){
        this.bookingServiceImpl = bookingServiceImpl;
        this.tableServiceImpl = tableServiceImpl;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/create"
    )
    public ResponseEntity<String> createBooking(@RequestBody ReservationRequestDTO reservationRequestDTO){
            boolean created = bookingServiceImpl.save(reservationRequestDTO);
        if (created) {
            return ResponseEntity.ok("Booking created successfully");
        } else {
            return ResponseEntity.badRequest().body("Table is already booked for the specified date and time");
        }

    }






}
