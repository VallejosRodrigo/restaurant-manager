package com.softchar.restaurant_manager.application.service;

import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import com.softchar.restaurant_manager.infrastructure.adapter.BookingJpaAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingJpaAdapter bookingJpaAdapter;

    @InjectMocks
    private BookingServiceImpl bookingServiceImpl;

    @Test
    void save() {
    }

    @Test
    void updateById() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAllByName() {
    }

    @Test
    void findAllBookings() {
    }

    @Test
    void validateFieldsGetAllBooking() {
    }
}