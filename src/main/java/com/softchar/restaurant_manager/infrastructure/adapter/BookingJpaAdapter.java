package com.softchar.restaurant_manager.infrastructure.adapter;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.Table;
import com.softchar.restaurant_manager.domain.port.repository.BookingRepositoryPort;
import com.softchar.restaurant_manager.infrastructure.adapter.entity.BookingEntity;
import com.softchar.restaurant_manager.infrastructure.adapter.entity.TableEntity;
import com.softchar.restaurant_manager.infrastructure.adapter.mapper.BookingDboMapper;
import com.softchar.restaurant_manager.infrastructure.adapter.mapper.TableDboMapper;
import com.softchar.restaurant_manager.infrastructure.adapter.repository.BookingJpaRepository;
import com.softchar.restaurant_manager.infrastructure.adapter.repository.TableJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingJpaAdapter implements BookingRepositoryPort {

    private final BookingJpaRepository jpaBookingRepository;
    private final TableJpaRepository jpaTableRepository;
    private final BookingDboMapper bookingDboMapper;
    private final TableDboMapper tableDboMapper;

    @Autowired
    public BookingJpaAdapter(
            BookingJpaRepository jpaBookingRepository,
            TableJpaRepository jpaTableRepository,
            TableDboMapper tableDboMapper,
            BookingDboMapper bookingDboMapper
    ) {
        this.jpaBookingRepository = jpaBookingRepository;
        this.jpaTableRepository = jpaTableRepository;
        this.bookingDboMapper = bookingDboMapper;
        this.tableDboMapper = tableDboMapper;
    }

    @Override
    public Booking findById(Long id) {
        return null;
    }

    @Override
    public Booking save(Booking booking) {

        TableEntity tableEntity = jpaTableRepository.findById(booking.getTable().getId())
                .orElseThrow(() -> new IllegalArgumentException("Table with ID " + booking.getTable().getId() + " not found"));


        boolean tableOccupied = jpaBookingRepository.existsByTableAndReservationDateAndReservationTime(
                tableEntity, booking.getReservationDate(), booking.getReservationTime());

        if (!tableOccupied) {
            BookingEntity newBooking = bookingDboMapper.toDbo(booking);
            BookingEntity bookingSaved = jpaBookingRepository.save(newBooking);
            return bookingDboMapper.toDomain(bookingSaved);
        } else {
            // Manejar el caso cuando la mesa está ocupada para la fecha y hora especificadas
            throw new IllegalStateException("Table with ID " + booking.getTable().getId()   + " is already booked for the specified date and time");
        }
    }

}
