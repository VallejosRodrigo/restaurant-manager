package com.softchar.restaurant_manager.infrastructure.adapter.mapper;

import com.softchar.restaurant_manager.domain.model.Booking;
import com.softchar.restaurant_manager.infrastructure.adapter.entity.BookingEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingDboMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "customerDni", target = "customerDni")
    @Mapping(source = "table", target = "table")
    @Mapping(source = "reservationTime", target = "reservationTime")
    @Mapping(source = "reservationDate", target = "reservationDate")
    BookingEntity toDbo(Booking domain);

    @InheritInverseConfiguration
    Booking toDomain(BookingEntity entity);

}
