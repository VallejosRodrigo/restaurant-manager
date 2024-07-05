package com.softchar.restaurant_manager.application.mapper;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.Table;
import com.softchar.restaurant_manager.domain.model.dto.BookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookingDtoMapper {

    @Mapping(source = "customerID", target = "customerID")
    @Mapping(source = "table", target = "tableID", qualifiedByName = "tableToTableId")
    @Mapping(source = "reservationTime", target = "reservationTime")
    @Mapping(source = "reservationDate", target = "reservationDate")
    @Mapping(source = "state", target = "state")
    BookingDto toDto(Booking domain);

    @Named("tableToTableId")
    default Long tableToTableId(Table table) {
        return table.getId();
    }
}
