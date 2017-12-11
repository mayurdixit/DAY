package org.DAY.service;

import org.DAY.db.entity.Inventory;
import org.DAY.repository.IInventoryRepository;
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
}
