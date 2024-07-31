package com.softchar.restaurant_manager.application.service;

import com.softchar.restaurant_manager.application.mapper.BookingDtoMapper;
import com.softchar.restaurant_manager.application.mapper.BookingRequestMapper;
import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.domain.model.dto.delete.BookingDelete;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import com.softchar.restaurant_manager.domain.port.repository.BookingRepositoryPort;
import com.softchar.restaurant_manager.domain.port.service.BookingService;
import com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception.IdCannotBeNullException;
import com.softchar.restaurant_manager.infrastructure.sahre.config.CaffeineCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public BookingDto updateById(Long id, BookingRequest request) {
        if(id != null) {
            Booking bookingToUpdateById = bookingRequestMapper.toDomain(request);
            Booking bookingUpdated = bookingRepositoryPort.updateById(id, bookingToUpdateById);
            return bookingDtoMapper.toDto(bookingUpdated);
        }
        else throw new IdCannotBeNullException("ID cannot be null");
    }

    @Cacheable(value = CaffeineCacheConfig.BOOKING_CACHE, key = "'findById_' + #id", unless = "#result == null")
    @Override
    public BookingDto findById(Long id){
        if(id != null){
            Booking bookingToFindById = bookingRepositoryPort.findById(id);
            return bookingDtoMapper.toDto(bookingToFindById);
        }
        else throw new IdCannotBeNullException("ID cannot be null");

    }

    @Override
    public BookingDelete deleteById(Long id) {
        if(id != null) {
            bookingRepositoryPort.deleteById(id);
            return new BookingDelete("Booking with id " + id + " has been deleted successfully");
        }
        else throw new IdCannotBeNullException("ID cannot be null");
    }

    @Cacheable(value = CaffeineCacheConfig.BOOKING_CACHE, key = "'findAllByName_' + #name", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<BookingDto> findAllByName(String name) {
        if(name != null) {
            List<Booking> bookingToFindByName = bookingRepositoryPort.findAllByName(name);
            return bookingToFindByName.stream()
                    .map(bookingDtoMapper::toDto)
                    .collect(Collectors.toList());
        }
        else throw new IdCannotBeNullException("customerName cannot be null");
    }

    @Cacheable(value = CaffeineCacheConfig.BOOKING_CACHE, key = "'findAllBookings_' + #pageable.pageNumber + '_' + #pageable.pageSize", unless = "#result == null || !#result.hasContent()")
    @Override
    public Page<BookingDto> findAllBookings(Pageable pageable) {


        Page<Booking> bookings = bookingRepositoryPort.findAll(pageable);
        return bookings.map(bookingDtoMapper::toDto);
    }

}
