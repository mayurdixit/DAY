/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory.controller;

import java.util.*;

import org.DAY.db.entity.KendraInfo;
import org.DAY.inventory.entity.EquipmentType;
import org.DAY.inventory.entity.Inventory;
import org.DAY.inventory.service.EquipmentTypeService;
import org.DAY.inventory.service.InventoryService;
import org.DAY.inventory.utility.InventoryData;
import org.DAY.service.KendraInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private KendraInfoService kendraInfoService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @RequestMapping(value = "/internal/add_inventory", method = RequestMethod.POST)
    public Inventory addInventory(@RequestBody Inventory inventoryData) {
        System.out.println("parameters = " + inventoryData);
        //Inventory inventory = inventoryData.getInventory();
        Date currDate = new Date();
        inventoryData.setCreatedOn(currDate);
        inventoryData.setLastUpdatedOn(currDate);
        inventoryService.addInventory(inventoryData);
        System.out.println("generate ID = " + inventoryData.getId());
        return inventoryData;
    }

    @RequestMapping(value = "/internal/update_inventory", method = RequestMethod.POST)
    public Inventory updateInventory(@RequestBody Inventory inventoryData) {
        Date updateDate = new Date();
        inventoryService.updateInventory(inventoryData, updateDate);
        return inventoryData;
    }

    @RequestMapping(value = "/internal/delete_inventory/{id}", method = RequestMethod.POST)
    @Transactional
    public boolean deleteInventory(@PathVariable String id) {
        inventoryService.deleteInventory(id);
        return true;
    }
    @RequestMapping(value = "/internal/inventory/{id}", method = RequestMethod.GET)
    public Inventory getEquipmentInfo(@PathVariable String id) {
        Inventory inventory = null;
        Optional<Inventory> inventoryObservable = inventoryService.getInvntoryRecord(id);
        if(inventoryObservable.isPresent()){
            inventory = inventoryObservable.get();
        }
        return inventory;
    }

    @RequestMapping(value="/internal/all_equipment_types", method = RequestMethod.GET)
    public List<EquipmentType> getAllEquipmentTypes(){
        return  equipmentTypeService.getAllEquipmentType();
    }

    @RequestMapping(value = "/internal/kendra_inventory/{id}", method = RequestMethod.GET)
    public List<InventoryData> getKendraInventory(@RequestHeader(value="auth-token", defaultValue = "") String
        authToken,
        @PathVariable
        String id) {
        //System.out.println("authToken=" + authToken);
        List<InventoryData> kendraInventory = new ArrayList<>();
        List<Inventory> inventoryList = inventoryService.getInventoryByKendra(id);
        System.out.println("inventoryList length= " + inventoryList.size());
        inventoryList.forEach(inventory -> {
            InventoryData inventoryData = new InventoryData();
            KendraInfo kendraData = getKendraInfo(inventory.getKendraId());
            KendraInfo zoneInfo = null;
            if (kendraData != null) {
                zoneInfo = getKendraInfo((kendraData.getParent()));
            }
            inventoryData.setInventory(inventory);
            inventoryData.setKendraInfo(kendraData);
            inventoryData.setZoneInfo(zoneInfo);
            kendraInventory.add(inventoryData);
        });
        return kendraInventory;
    }

    private KendraInfo getKendraInfo(Integer id) {
        KendraInfo kendraInfo;
        if (id != null) {
            Optional<KendraInfo> kendraInfoOptional = kendraInfoService.getKendraInfo(id);
            if (kendraInfoOptional.isPresent()) {
                kendraInfo = kendraInfoOptional.get();
            } else {
                kendraInfo = new KendraInfo();
            }
        } else {
            kendraInfo = new KendraInfo();
        }
        return kendraInfo;
    }
}
