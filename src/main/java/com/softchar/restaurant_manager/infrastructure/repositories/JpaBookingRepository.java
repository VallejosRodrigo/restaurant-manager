package com.softchar.restaurant_manager.infrastructure.repositories;

import com.softchar.restaurant_manager.infrastructure.entities.BookingEntity;
import com.softchar.restaurant_manager.infrastructure.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface JpaBookingRepository extends JpaRepository<BookingEntity, Long> {

    boolean existsByTableAndReservationDateAndReservationTime(TableEntity table, LocalDate reservationDate, LocalTime reservationTime);
}
