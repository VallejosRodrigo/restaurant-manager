package com.softchar.restaurant_manager.domain.port.service;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;

public interface BookingService {

    BookingDto save(BookingRequest request);

}
