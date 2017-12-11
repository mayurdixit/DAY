package org.DAY.repository;

import org.DAY.db.entity.InventoryContact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface IInventoryContactRepository extends CrudRepository<InventoryContact, Integer> {

    @Query("select ic from InventoryContact ic where ic.InventoryId = :id")
    Iterable<InventoryContact> findByInventoryId(int id);

    @Transactional
    @Query("delete from InventoryContact ic where ic.InventoryId = :id")
    void deleteByInventoryId(int id);
}
