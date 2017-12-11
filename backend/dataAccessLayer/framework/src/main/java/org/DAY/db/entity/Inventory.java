package org.DAY.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="inventory", schema="parth_preeti")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String serialModelNumber;
    private boolean inUse;
    private Date purchasedOn;
    private Date usedSince;
    private String storedAt;
    private String kendraId;
    private String comment;
    private Date createdOn;
    private Date lastUpdatedOn;
    private int updatedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getKendraId() {
        return kendraId;
    }

    public void setKendraId(String kendraId) {
        this.kendraId = kendraId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialModelNumber='" + serialModelNumber + '\'' +
                ", inUse=" + inUse +
                ", purchasedOn=" + purchasedOn +
                ", usedSince=" + usedSince +
                ", storedAt='" + storedAt + '\'' +
                ", kendraId='" + kendraId + '\'' +
                ", comment='" + comment + '\'' +
                ", createdOn=" + createdOn +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", updatedBy=" + updatedBy +
                '}';
    }
}
