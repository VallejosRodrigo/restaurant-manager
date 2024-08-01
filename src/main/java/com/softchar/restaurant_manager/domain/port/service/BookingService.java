package com.softchar.restaurant_manager.domain.port.service;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.delete.BookingDelete;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {

    BookingDto save(BookingRequest request);
    BookingDto updateById(Long id, BookingRequest request);
    BookingDto findById(Long id);
    BookingDelete deleteById(Long id);
    List<BookingDto> findAllByName(String name);
    Page<BookingDto> findAllBookings(Pageable pageable);

    void validateFieldsGetAllBooking(int page, int size, String sortBy);

}