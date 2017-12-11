package org.DAY.repository;

import org.DAY.db.entity.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IInventoryRepository extends CrudRepository<Inventory, Integer> {

    @Query("select i from Inventory i where i.kendraId = :id")
    Iterable<Inventory> findByKendraId(int id);
}
