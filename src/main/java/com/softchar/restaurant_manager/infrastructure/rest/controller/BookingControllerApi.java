package com.softchar.restaurant_manager.infrastructure.rest.controller;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.delete.BookingDelete;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/booking")
public interface BookingControllerApi {

    @Operation(
            summary = "Create a new booking"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "conflict: a reservation already exists on that date or time"),
            @ApiResponse(responseCode = "500", description = "Internal server error T_T ")
    })
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/"
    )
    ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequest request);


    @Operation(
            summary = "Update an existing booking by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Conflict: a reservation already exists on that date or time"),
            @ApiResponse(responseCode = "500", description = "Internal server error T_T ")
    })
    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/update/{id}"
    )
    ResponseEntity<BookingDto> updateBookingById(@PathVariable Long id,
                                                 @RequestBody BookingRequest request);


    @Operation(
            summary = "Find a booking by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Booking not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error T_T ")
    })
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/get/{id}"
    )
    ResponseEntity<BookingDto> findBookingById(@PathVariable Long id);


    @Operation(
            summary = "Delete a booking by ID"
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/delete/{id}"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Booking not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error T_T ")
    })
    ResponseEntity<BookingDelete> deleteBookingById(@PathVariable Long id);


    @Operation(
            summary = "Find all bookings by customer name containing"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Bookings not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error T_T ")
    })
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{customerName}"
    )
    ResponseEntity<List<BookingDto>> findAllBookingByNameContaining(@PathVariable String customerName);


    @Operation(
            summary = "Get all bookings with pagination and sorting"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error T_T ")
    })
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/"
    )
    ResponseEntity<Page<BookingDto>> getAllBookings(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size,
                                                    @RequestParam(defaultValue = "id") String sortBy);



}
