/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.db.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 204048703 on 12/15/2017.
 */
@Entity
@Table(name="user_app_role", schema = "parth_preeti")
public class UserAppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int appId;
    private int roleId;
    private int kendraId;
    private Date createdOn;
    private Date lastUpdatedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public int getKendraId() {
        return kendraId;
    }

    public void setKendraId(int kendraId) {
        this.kendraId = kendraId;
    }

    @Override
    public String toString() {
        return "UserAppRole{" +
            "id=" + id +
            ", userId=" + userId +
            ", appId=" + appId +
            ", roleId=" + roleId +
            ", kendraId=" + kendraId +
            ", createdOn=" + createdOn +
            ", lastUpdatedOn=" + lastUpdatedOn +
            '}';
    }
}
