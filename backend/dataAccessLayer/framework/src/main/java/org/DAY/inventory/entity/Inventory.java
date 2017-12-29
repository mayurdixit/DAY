package org.DAY.inventory.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="inventory", schema="parth_preeti")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int equipmentType;
    private String serialModelNumber;
    private boolean inUse;
    private Date purchasedOn;
    private Date usedSince;
    private String storedAt;
    private int kendraId;
    private String comment;
    private Date createdOn;
    private Date lastUpdatedOn;
    private int updatedBy;
    private String contact1_name;
    private String contact1_phone;
    private String contact1_email;
    private String contact2_name;
    private String contact2_phone;
    private String contact2_email;
    private String ownedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return equipmentType;
    }

    public void setName(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getSerialModelNumber() {
        return serialModelNumber;
    }

    public void setSerialModelNumber(String serialModelNumber) {
        this.serialModelNumber = serialModelNumber;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public Date getPurchasedOn() {
        return purchasedOn;
    }

    public void setPurchasedOn(Date purchasedOn) {
        this.purchasedOn = purchasedOn;
    }

    public Date getUsedSince() {
        return usedSince;
    }

    public void setUsedSince(Date usedSince) {
        this.usedSince = usedSince;
    }

    public String getStoredAt() {
        return storedAt;
    }

    public void setStoredAt(String storedAt) {
        this.storedAt = storedAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getKendraId() {
        return kendraId;
    }

    public void setKendraId(int kendraId) {
        this.kendraId = kendraId;
    }

    public String getContact1_name() {
        return contact1_name;
    }

    public void setContact1_name(String contact1_name) {
        this.contact1_name = contact1_name;
    }

    public String getContact1_phone() {
        return contact1_phone;
    }

    public void setContact1_phone(String contact1_phone) {
        this.contact1_phone = contact1_phone;
    }

    public String getContact1_email() {
        return contact1_email;
    }

    public void setContact1_email(String contact1_email) {
        this.contact1_email = contact1_email;
    }

    public String getContact2_name() {
        return contact2_name;
    }

    public void setContact2_name(String contact2_name) {
        this.contact2_name = contact2_name;
    }

    public String getContact2_phone() {
        return contact2_phone;
    }

    public void setContact2_phone(String contact2_phone) {
        this.contact2_phone = contact2_phone;
    }

    public String getContact2_email() {
        return contact2_email;
    }

    public void setContact2_email(String contact2_email) {
        this.contact2_email = contact2_email;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", equipmentType=" + equipmentType +
                ", serialModelNumber='" + serialModelNumber + '\'' +
                ", inUse=" + inUse +
                ", purchasedOn=" + purchasedOn +
                ", usedSince=" + usedSince +
                ", storedAt='" + storedAt + '\'' +
                ", kendraId=" + kendraId +
                ", comment='" + comment + '\'' +
                ", createdOn=" + createdOn +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", updatedBy=" + updatedBy +
                ", contact1_name='" + contact1_name + '\'' +
                ", contact1_phone='" + contact1_phone + '\'' +
                ", contact1_email='" + contact1_email + '\'' +
                ", contact2_name='" + contact2_name + '\'' +
                ", contact2_phone='" + contact2_phone + '\'' +
                ", contact2_email='" + contact2_email + '\'' +
                ", ownedBy='" + ownedBy + '\'' +
                '}';
    }
}
