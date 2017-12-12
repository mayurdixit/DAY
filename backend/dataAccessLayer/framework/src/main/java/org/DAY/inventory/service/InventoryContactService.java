/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory.service;

import org.DAY.inventory.entity.InventoryContact;
import org.DAY.inventory.repository.IInventoryContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InventoryContactService {

    @Autowired
    private IInventoryContactRepository inventoryContactRepository;

    public List<InventoryContact> getInventoryContactByInventoryId(int id){
        List<InventoryContact> contactsForInventory = new ArrayList<>();
        inventoryContactRepository.findByInventoryId(id).forEach(contactsForInventory::add);
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

    public void updateInventoryContact(InventoryContact inventoryContact, Date updateDate) {
        inventoryContact.setLastUpdatedOn(updateDate);
        inventoryContactRepository.save(inventoryContact);
    }
}
