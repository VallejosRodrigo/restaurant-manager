package com.softchar.restaurant_manager.infrastructure.repositories;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.Table;
import com.softchar.restaurant_manager.domain.model.dto.ReservationRequestDTO;
import com.softchar.restaurant_manager.domain.port.repository.BookingRepositoryPort;
import com.softchar.restaurant_manager.infrastructure.adapter.mapper.BookingMapper;
import com.softchar.restaurant_manager.infrastructure.entities.TableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaBookingRepositoryAdapter implements BookingRepositoryPort {

    private final JpaBookingRepository jpaBookingRepository;
    private final JpaTableRepository jpaTableRepository;

    @Autowired
    public JpaBookingRepositoryAdapter(JpaBookingRepository jpaBookingRepository, JpaTableRepository jpaTableRepository) {
        this.jpaBookingRepository = jpaBookingRepository;
        this.jpaTableRepository = jpaTableRepository;
    }

    @Override
    public boolean save(ReservationRequestDTO reservation) {
        Optional<TableEntity> tableOpt = jpaTableRepository.findById(reservation.getTableID());

        if (tableOpt.isEmpty()) {
            // Manejar el caso cuando la mesa no existe
            throw new IllegalArgumentException("Table with ID " + reservation.getTableID() + " not found");
        }

        Table table = mapTableEntityToDomain(tableOpt.get());
        boolean tableOccupied = jpaBookingRepository.existsByTableAndReservationDateAndReservationTime(
                table, reservation.getReservationDate(), reservation.getReservationTime());

        if (!tableOccupied) {
            Booking newBooking = BookingMapper.toDomain(reservation);
            jpaBookingRepository.save(BookingMapper.toEntity(newBooking, table));
            return true;
        } else {
            // Manejar el caso cuando la mesa está ocupada para la fecha y hora especificadas
            throw new IllegalStateException("Table with ID " + reservation.getTableID() + " is already booked for the specified date and time");
        }
    }

    private Table mapTableEntityToDomain(TableEntity tableEntity) {
        return new Table(tableEntity.getId(), tableEntity.getNumber(), tableEntity.getCapacity());
    }
}
