/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory.utility;

import java.util.List;

import org.DAY.inventory.entity.Inventory;
import org.DAY.inventory.entity.InventoryContact;

/**
 * Created by 204048703 on 12/11/2017.
 */
public class InventoryData {
    private Inventory inventory;
    private List<InventoryContact> contactList;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<InventoryContact> getContactList() {
        return contactList;
    }

    public void setContactList(List<InventoryContact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "InventoryData{" +
            "inventory=" + inventory +
            ", contactList=" + contactList +
            '}';
    }
}
