package com.softchar.restaurant_manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    private long id;
    private int number;
    private int capacity;
    private List<Booking> bookings;
}
