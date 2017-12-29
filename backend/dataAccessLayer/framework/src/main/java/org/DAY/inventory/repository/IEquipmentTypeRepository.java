package org.DAY.inventory.repository;

import org.DAY.inventory.entity.EquipmentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipmentTypeRepository extends CrudRepository<EquipmentType, Integer> {
}
