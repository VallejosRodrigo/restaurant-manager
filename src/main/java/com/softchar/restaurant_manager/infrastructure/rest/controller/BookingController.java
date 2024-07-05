package com.softchar.restaurant_manager.infrastructure.rest.controller;

import com.softchar.restaurant_manager.application.service.BookingServiceImpl;
import com.softchar.restaurant_manager.application.service.TableServiceImpl;
import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
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
            path = "/"
    )
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequest request){
        return new ResponseEntity<>(bookingServiceImpl.save(request), HttpStatus.CREATED);
    }






}
