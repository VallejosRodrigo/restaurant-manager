package com.softchar.restaurant_manager.application.mapper;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.domain.model.Table;
import com.softchar.restaurant_manager.domain.model.dto.request.BookingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookingRequestMapper {
    @Mapping(source = "customerDni", target = "customerDni")
    @Mapping(source = "customerName", target = "customerName")
    @Mapping(source = "tableID", target = "table", qualifiedByName = "tableIdToTable")
    @Mapping(source = "reservationTime", target = "reservationTime")
    @Mapping(source = "reservationDate", target = "reservationDate")
    Booking toDomain(BookingRequest request);

    @Named("tableIdToTable")
    default Table tableIdToTable(Long tableId) {
        //Implementar la busqueda de la mesa
        return new Table(tableId, 0, 0);
    }

}
