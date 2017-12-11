/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory.repository;

import org.DAY.inventory.entity.InventoryContact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface IInventoryContactRepository extends CrudRepository<InventoryContact, Integer> {

    @Query("select ic from InventoryContact ic where ic.inventoryId = :id")
    Iterable<InventoryContact> findByInventoryId(int id);

    @Transactional
    @Modifying
    @Query("delete from InventoryContact ic where ic.inventoryId = :id")
    void deleteByInventoryId(int id);
}
