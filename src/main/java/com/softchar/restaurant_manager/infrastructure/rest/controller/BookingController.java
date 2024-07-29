package com.softchar.restaurant_manager.infrastructure.rest.controller;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.delete.BookingDelete;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import com.softchar.restaurant_manager.domain.port.service.BookingService;
import com.softchar.restaurant_manager.domain.port.service.TableService;
import com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
            method = RequestMethod.PUT,
            path = "/update/{id}"
    )
    public ResponseEntity<BookingDto> updateBookingById(@PathVariable Long id, @RequestBody BookingRequest request){
        return new ResponseEntity<>(bookingService.updateById(id, request), HttpStatus.OK);
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
            path = "/delete/{id}"
    )
    public ResponseEntity<BookingDelete> deleteBookingById(@PathVariable Long id){
        return new ResponseEntity<>(bookingService.deleteById(id), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{name}"
    )
    public ResponseEntity<List<BookingDto>> findAllBookingByNameContaining(@PathVariable String name){

        List<BookingDto> bookingDtoList = bookingService.findAllByName(name.toUpperCase());
        return new ResponseEntity<>(bookingDtoList, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/"
    )
    public ResponseEntity<Page<BookingDto>> getAllBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id") String sortBy){

        validateFieldsGetAllBooking(page, size, sortBy);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<BookingDto> bookingDtoPage = bookingService.findAllBookings(pageable);
        return new ResponseEntity<>(bookingDtoPage ,HttpStatus.OK);
    }


    private void validateFieldsGetAllBooking(int page, int size, String sortBy){
        List<String> validSortByFields = Arrays.asList(
                "id", "customerDni", "customerName", "tableID",
                "reservationDate", "reservationTime", "state");
        if (page < 0)
            throw new MethodArgumentNotValidException("Value of the 'page' parameter must not be less than 0");
        if(size <= 0)
            throw new MethodArgumentNotValidException("Value of the 'size' parameter must be greater than 0");
        if(!validSortByFields.contains(sortBy))
            throw new MethodArgumentNotValidException("You must enter a value in 'sortBy' that is compatible with some field");
    }


}
