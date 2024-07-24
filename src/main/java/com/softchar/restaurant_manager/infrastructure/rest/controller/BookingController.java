package com.softchar.restaurant_manager.infrastructure.rest.controller;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.delete.BookingDelete;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import com.softchar.restaurant_manager.domain.port.service.BookingService;
import com.softchar.restaurant_manager.domain.port.service.TableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    private final TableService tableService;

    public BookingController(BookingService bookingService, TableService tableService){
        this.bookingService = bookingService;
        this.tableService = tableService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/"
    )
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequest request){
        return new ResponseEntity<>(bookingService.save(request), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/get/{id}"
    )
    public ResponseEntity<BookingDto> findBookingById(@PathVariable Long id){
        return new ResponseEntity<>(bookingService.findById(id),HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete/{id}"
    )
    public ResponseEntity<BookingDelete> deleteBookingById(@PathVariable Long id){
        return new ResponseEntity<>(bookingService.deleteById(id), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/"
    )
    public ResponseEntity<Page<BookingDto>> getAllBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "reservationDate") String sortBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<BookingDto> bookingDtoPage = bookingService.findAllBookings(pageable);
        return new ResponseEntity<>(bookingDtoPage ,HttpStatus.OK);
    }




}
