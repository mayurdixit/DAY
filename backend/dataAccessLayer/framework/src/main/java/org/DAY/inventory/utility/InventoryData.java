/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory.utility;

import org.DAY.db.entity.KendraInfo;
import org.DAY.inventory.entity.Inventory;

/**
 * Created by 204048703 on 12/11/2017.
 */
public class InventoryData {
    private Inventory inventory;
    private KendraInfo zoneInfo;
    private KendraInfo kendraInfo;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public KendraInfo getZoneInfo() {
        return zoneInfo;
    }

    public void setZoneInfo(KendraInfo zoneInfo) {
        this.zoneInfo = zoneInfo;
    }

    public KendraInfo getKendraInfo() {
        return kendraInfo;
    }

    public void setKendraInfo(KendraInfo kendraInfo) {
        this.kendraInfo = kendraInfo;
    }

    @Override
    public String toString() {
        return "InventoryData{" +
            "inventory=" + inventory +
            ", zoneInfo=" + zoneInfo +
            ", kendraInfo=" + kendraInfo +
            '}';
    }
}
