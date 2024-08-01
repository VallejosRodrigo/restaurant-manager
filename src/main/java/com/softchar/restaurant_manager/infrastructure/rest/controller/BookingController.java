package com.softchar.restaurant_manager.infrastructure.rest.controller;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.delete.BookingDelete;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import com.softchar.restaurant_manager.domain.port.service.BookingService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController implements BookingControllerApi {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @Override
    public ResponseEntity<BookingDto> createBooking(BookingRequest request){
        return new ResponseEntity<>(bookingService.save(request), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookingDto> updateBookingById(@NotNull Long id,
                                                        BookingRequest request){
        return new ResponseEntity<>(bookingService.updateById(id, request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDto> findBookingById(@NotNull Long id){
        return new ResponseEntity<>(bookingService.findById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDelete> deleteBookingById(@NotNull Long id){
        return new ResponseEntity<>(bookingService.deleteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookingDto>> findAllBookingByNameContaining(@NotNull String customerName){
        List<BookingDto> bookingDtoList = bookingService.findAllByName(customerName.toUpperCase());
        return new ResponseEntity<>(bookingDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<BookingDto>> getAllBookings(int page,
                                                           int size,
                                                           String sortBy) {
        bookingService.validateFieldsGetAllBooking(page, size, sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<BookingDto> bookingDtoPage = bookingService.findAllBookings(pageable);

        return new ResponseEntity<>(bookingDtoPage ,HttpStatus.OK);
    }

}
