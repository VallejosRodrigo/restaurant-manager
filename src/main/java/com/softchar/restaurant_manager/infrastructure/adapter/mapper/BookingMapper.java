package com.softchar.restaurant_manager.infrastructure.adapter.mapper;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.Table;
import com.softchar.restaurant_manager.infrastructure.entities.BookingEntity;
import com.softchar.restaurant_manager.infrastructure.entities.TableEntity;

public class BookingMapper {

    public static Booking toDomain(BookingEntity entity){
        return Booking.builder()
                .id(entity.getId())
                .customerID(entity.getCustomerID())
                .reservationDate(entity.getReservationDate())
                .reservationTime(entity.getReservationTime())
                .table(mapTableEntityToTable(entity.getTable()))
                .state(entity.getState())
                .build();
    }

    public static BookingEntity toEntity(Booking booking, Table table){
        return BookingEntity.builder()
                .id(booking.getId())
                .customerID(booking.getCustomerID())
                .reservationDate(booking.getReservationDate())
                .reservationTime(booking.getReservationTime())
                .table(mapTableToTableEntity(table))
                .state(booking.getState())
                .build();
    }

    // Método para mapear TableEntity a Table, si es necesario
    private static Table mapTableEntityToTable(TableEntity tableEntity) {
        return Table.builder()
                .id(tableEntity.getId())
                .number(tableEntity.getNumber())
                .capacity(tableEntity.getCapacity())
                .build();
    }

    // Método para mapear Table a TableEntity
    private static TableEntity mapTableToTableEntity(Table table) {
        TableEntity tableEntity = new TableEntity();
        tableEntity.setId(table.getId());
        tableEntity.setNumber(table.getNumber());
        tableEntity.setCapacity(table.getCapacity());
        return tableEntity;
    }

}
