package com.softchar.restaurant_manager.domain.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Schema(description = "Customer DNI", example = "12345678")
    private Long customerDni;

    @NotNull
    @Schema(description = "Customer Name", example = "Vallejos Rodrigo E")
    private String customerName;

    @NotNull
    @Schema(description = "Table ID", example = "2")
    private Long tableID;

    @NotNull
    @Schema(description = "Reservation Date", example = "2024-08-01")
    private LocalDate reservationDate;

    @NotNull
    @Schema(description = "Reservation Time", example = "19:30:00")
    private LocalTime reservationTime;
}
