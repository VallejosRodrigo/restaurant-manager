package com.softchar.restaurant_manager.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerID;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private String state;
}
