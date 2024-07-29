package com.softchar.restaurant_manager.domain.port.repository;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingRepositoryPort {

    Booking save(Booking request);
    Booking updateById(Long id, Booking request);
    Booking findById(Long id);
    void deleteById(Long id);
    List<Booking> findAllByName(String name);
    Page<Booking> findAll(Pageable pageable);

}
