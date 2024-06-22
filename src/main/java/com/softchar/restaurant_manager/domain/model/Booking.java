package com.softchar.restaurant_manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long id;
    private String clientName;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private String state;
    private Table table;
}
