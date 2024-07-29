package com.softchar.restaurant_manager.infrastructure.adapter.repository;

import com.softchar.restaurant_manager.infrastructure.adapter.entity.BookingEntity;
import com.softchar.restaurant_manager.infrastructure.adapter.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {

    boolean existsByTableAndReservationDateAndReservationTime(TableEntity table, LocalDate reservationDate, LocalTime reservationTime);
    List<BookingEntity> findAllByCustomerNameContaining(String name);
}
