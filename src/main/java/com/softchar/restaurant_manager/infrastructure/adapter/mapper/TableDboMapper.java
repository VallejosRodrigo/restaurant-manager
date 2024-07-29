package com.softchar.restaurant_manager.infrastructure.adapter.mapper;

import com.softchar.restaurant_manager.domain.model.Table;
import com.softchar.restaurant_manager.infrastructure.adapter.entity.TableEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TableDboMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "capacity", target = "capacity")
    TableEntity toDbo(Table domain);

    @InheritInverseConfiguration
    Table toDomain(TableEntity entity);
}
