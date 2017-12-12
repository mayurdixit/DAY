/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.DAY.inventory.entity.Inventory;
import org.DAY.inventory.entity.InventoryContact;
import org.DAY.inventory.service.InventoryContactService;
import org.DAY.inventory.service.InventoryService;
import org.DAY.inventory.utility.InventoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryContactService inventoryContactService;

    @RequestMapping(value = "/internal/add_inventory", method = RequestMethod.POST)
    public void addInventory(@RequestBody InventoryData inventoryData){
        System.out.println("parameters = " + inventoryData);
        Inventory inventory = inventoryData.getInventory();
        Date currDate = new Date();
        inventory.setCreatedOn(currDate);
        inventory.setLastUpdatedOn(currDate);
        inventoryService.addInventory(inventory);
        System.out.println("generate ID = " + inventory.getId());

        List<InventoryContact> inventoryContactList = inventoryData.getContactList();
        inventoryContactList.forEach(inventoryContact -> {
            inventoryContact.setCreatedOn(currDate);
            inventoryContact.setLastUpdatedOn(currDate);
            inventoryContact.setInventoryId(inventory.getId());
            inventoryContactService.addInventoryContact(inventoryContact);
        });
    }

    @RequestMapping(value = "/internal/update_inventory", method = RequestMethod.POST)
    public InventoryData updateInventory(@RequestBody InventoryData inventoryData){
        Inventory inventory = inventoryData.getInventory();
        List<InventoryContact> inventoryContactList = inventoryData.getContactList();
        Date updateDate = new Date();
        inventoryService.updateInventory(inventory, updateDate);
        inventoryContactList.forEach(inventoryContact -> {
            inventoryContactService.updateInventoryContact(inventoryContact, updateDate);
        });
        return inventoryData;
    }

    @RequestMapping(value = "/internal/delete_inventory/{id}", method = RequestMethod.POST)
    @Transactional
    public void deleteInventory(@PathVariable String id){
        inventoryContactService.deleteInventoryContactForInvntory(id);
        inventoryService.deleteInventory(id);
        return;
    }

    @RequestMapping(value = "/internal/kendra_inventory/{id}", method = RequestMethod.GET)
    public List<InventoryData> getKendraInventory(@PathVariable String id){
        List<InventoryData> kendraInventory = new ArrayList<>();
        List<Inventory> inventoryList = inventoryService.getInventoryByKendra(id);
        inventoryList.forEach(inventory -> {
            List<InventoryContact> inventoryContactList = inventoryContactService.getInventoryContactByInventoryId
                (inventory.getId());
            InventoryData inventoryData = new InventoryData();
            inventoryData.setInventory(inventory);
            inventoryData.setContactList(inventoryContactList);
            kendraInventory.add(inventoryData);
        });
        return kendraInventory;
    }
}
