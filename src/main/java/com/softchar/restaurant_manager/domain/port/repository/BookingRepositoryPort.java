package com.softchar.restaurant_manager.domain.port.repository;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;

public interface BookingRepositoryPort {

    Booking findById(Long id);
    Booking save(Booking request);

}
