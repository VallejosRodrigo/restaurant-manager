package com.softchar.restaurant_manager.application;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.dto.ReservationRequestDTO;
import com.softchar.restaurant_manager.domain.port.repository.BookingRepositoryPort;
import com.softchar.restaurant_manager.domain.port.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepositoryPort bookingRepositoryPort;

    @Override
    public boolean save(ReservationRequestDTO reservation) {
        return bookingRepositoryPort.save(ReservationRequestDTO.builder().build());
    }
}
