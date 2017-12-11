/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.inventory;

import java.util.Date;

/**
 * Created by 204048703 on 12/11/2017.
 */
public class AddInventoryParam {

    private String  inventoryName;
    private String  inventorySerialNumber;
    private boolean inventoryInUse;
    private Date    inventoryPurchasedOn;
    private Date    inventoryUsedSince;
    private String  inventoryStoredAt;
    private String  inventoryComment;
    private int     inventoryUpdatedBy;
    private int     inventoryKendraId;
    private String  contactName;
    private String  contactNumber;
    private String  contactEmail;

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getInventorySerialNumber() {
        return inventorySerialNumber;
    }

    public void setInventorySerialNumber(String inventorySerialNumber) {
        this.inventorySerialNumber = inventorySerialNumber;
    }

    public boolean isInventoryInUse() {
        return inventoryInUse;
    }

    public void setInventoryInUse(boolean inventoryInUse) {
        this.inventoryInUse = inventoryInUse;
    }

    public Date getInventoryPurchasedOn() {
        return inventoryPurchasedOn;
    }

    public void setInventoryPurchasedOn(Date inventoryPurchasedOn) {
        this.inventoryPurchasedOn = inventoryPurchasedOn;
    }

    public Date getInventoryUsedSince() {
        return inventoryUsedSince;
    }

    public void setInventoryUsedSince(Date inventoryUsedSince) {
        this.inventoryUsedSince = inventoryUsedSince;
    }

    public String getInventoryStoredAt() {
        return inventoryStoredAt;
    }

    public void setInventoryStoredAt(String inventoryStoredAt) {
        this.inventoryStoredAt = inventoryStoredAt;
    }

    public String getInventoryComment() {
        return inventoryComment;
    }

    public void setInventoryComment(String inventoryComment) {
        this.inventoryComment = inventoryComment;
    }

    public int getInventoryUpdatedBy() {
        return inventoryUpdatedBy;
    }

    public void setInventoryUpdatedBy(int inventoryUpdatedBy) {
        this.inventoryUpdatedBy = inventoryUpdatedBy;
    }

    public int getInventoryKendraId() {
        return inventoryKendraId;
    }

    public void setInventoryKendraId(int inventoryKendraId) {
        this.inventoryKendraId = inventoryKendraId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "AddInventoryParam{" +
            "inventoryName='" + inventoryName + '\'' +
            ", inventorySerialNumber='" + inventorySerialNumber + '\'' +
            ", inventoryInUse=" + inventoryInUse +
            ", inventoryPurchasedOn=" + inventoryPurchasedOn +
            ", inventoryUsedSince=" + inventoryUsedSince +
            ", inventoryStoredAt='" + inventoryStoredAt + '\'' +
            ", inventoryComment='" + inventoryComment + '\'' +
            ", inventoryUpdatedBy=" + inventoryUpdatedBy +
            ", inventoryKendraId=" + inventoryKendraId +
            ", contactName='" + contactName + '\'' +
            ", contactNumber='" + contactNumber + '\'' +
            ", contactEmail='" + contactEmail + '\'' +
            '}';
    }
}
