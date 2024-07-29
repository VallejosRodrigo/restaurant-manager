package com.softchar.restaurant_manager.infrastructure.adapter.repository;

import com.softchar.restaurant_manager.infrastructure.adapter.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableJpaRepository extends JpaRepository<TableEntity, Long> {

}
