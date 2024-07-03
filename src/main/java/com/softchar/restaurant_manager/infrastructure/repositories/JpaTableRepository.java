package com.softchar.restaurant_manager.infrastructure.repositories;

import com.softchar.restaurant_manager.infrastructure.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTableRepository extends JpaRepository<TableEntity, Long> {

}
