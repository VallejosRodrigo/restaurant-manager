package com.softchar.restaurant_manager.domain.port.service;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    BookingDto save(BookingRequest request);
    BookingDto findById(Long id);
    Page<BookingDto> findAllBookings(Pageable pageable);

}
