/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory.service;

import org.DAY.inventory.entity.Inventory;
import org.DAY.inventory.repository.IInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private IInventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory(){
        List<Inventory> allInventoryRecords = new ArrayList<>();
        inventoryRepository.findAll().forEach(allInventoryRecords::add);
        return allInventoryRecords;
    }

    public Optional<Inventory> getInvntoryRecord(String id){
        int inventoryId = Integer.parseInt(id);
        return inventoryRepository.findById(inventoryId);
    }

    public List<Inventory> getInventoryByKendra(String id){
        int kendraId = Integer.parseInt(id);
        List<Inventory> inventoryForKendra = new ArrayList<>();
        inventoryRepository.findByKendraId(kendraId).forEach(inventoryForKendra::add);
        return inventoryForKendra;
    }

    public void addInventory(Inventory inventory){
        inventory.setCreatedOn(new Date());
        inventory.setLastUpdatedOn(new Date());
        inventoryRepository.save(inventory);
    }

    public void deleteInventory(String id){
        int invntoryId = Integer.parseInt(id);
        inventoryRepository.deleteById(invntoryId);
    }

    public void updateInventory(Inventory inventory, Date updateDate){
        inventory.setLastUpdatedOn(updateDate);
        inventoryRepository.save(inventory);
    }
}
