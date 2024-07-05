package com.softchar.restaurant_manager.application.service;

import com.softchar.restaurant_manager.application.mapper.BookingDtoMapper;
import com.softchar.restaurant_manager.application.mapper.BookingRequestMapper;
import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import com.softchar.restaurant_manager.domain.port.repository.BookingRepositoryPort;
import com.softchar.restaurant_manager.domain.port.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepositoryPort bookingRepositoryPort;
    private final BookingDtoMapper bookingDtoMapper;
    private final BookingRequestMapper bookingRequestMapper;

    @Autowired
    public BookingServiceImpl(
            BookingRepositoryPort bookingRepositoryPort,
            BookingDtoMapper bookingDtoMapper,
            BookingRequestMapper bookingRequestMapper
    ) {
        this.bookingRepositoryPort = bookingRepositoryPort;
        this.bookingDtoMapper = bookingDtoMapper;
        this.bookingRequestMapper = bookingRequestMapper;
    }

    @Override
    public BookingDto save(BookingRequest request) {
        Booking bookingToSave = bookingRequestMapper.toDomain(request);

        Booking savedBooking = bookingRepositoryPort.save(bookingToSave);

        return bookingDtoMapper.toDto(savedBooking);
    }
}
