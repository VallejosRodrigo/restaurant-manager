package com.softchar.restaurant_manager.domain.model.dto.request;

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
public class BookingRequest {
    private Long customerDni;
    private String customerName;
    private Long tableID;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
}
