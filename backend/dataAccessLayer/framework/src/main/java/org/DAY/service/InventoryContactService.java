package org.DAY.service;

import org.DAY.db.entity.InventoryContact;
import org.DAY.repository.IInventoryContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InventoryContactService {

    @Autowired
    private IInventoryContactRepository inventoryContactRepository;

    List<InventoryContact> getInventoryContactByInventoryId(String id){
        int inventoryId = Integer.parseInt(id);
        List<InventoryContact> contactsForInventory = new ArrayList<>();

        inventoryContactRepository.findByInventoryId(inventoryId).forEach(contactsForInventory::add);
        return contactsForInventory;
    }

    public void addInventoryContact(InventoryContact inventoryContact){
        inventoryContact.setCreatedOn(new Date());
        inventoryContact.setLastUpdatedOn(new Date());

        inventoryContactRepository.save(inventoryContact);
    }

    public void deleteInventoryContact(String id){
        int inventoryContactId = Integer.parseInt(id);
        inventoryContactRepository.deleteById(inventoryContactId);
    }

    public void deleteInventoryContactForInvntory(String id){
        int inventoryContactId = Integer.parseInt(id);
        inventoryContactRepository.deleteByInventoryId(inventoryContactId);
    }
}
