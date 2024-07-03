package com.softchar.restaurant_manager.domain.port.repository;

import com.softchar.restaurant_manager.domain.model.dto.ReservationRequestDTO;

public interface BookingRepositoryPort {

    boolean save(ReservationRequestDTO reservation);
}
