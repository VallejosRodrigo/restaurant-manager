package com.softchar.restaurant_manager.domain.port.service;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.dto.ReservationRequestDTO;

public interface BookingService {

    boolean save(ReservationRequestDTO reservation);

}
