/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.utility;

import java.util.ArrayList;
import java.util.List;

import org.DAY.db.entity.AccessRole;
import org.DAY.db.entity.Applications;
import org.DAY.db.entity.KendraInfo;
import org.DAY.db.entity.User;

/**
 * Created by 204048703 on 12/8/2017.
 */
public class ACLInfo {
    private int userId;
    private String userName;
    private List<KendraInfo> zoneInfoList;
    private List<KendraInfo> kendraInfoList;
    private List<Applications> applicationList;
    private AccessRole accessRole;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<KendraInfo> getKendraInfoList() {
        if(kendraInfoList == null) {
            kendraInfoList = new ArrayList<>();
        }
        return kendraInfoList;
    }

    public void setKendraInfoList(List<KendraInfo> kendraInfoList) {
        this.kendraInfoList = kendraInfoList;
    }

    public List<KendraInfo> getZoneInfoList() {
        if(zoneInfoList == null){
            zoneInfoList = new ArrayList<>();
        }
        return zoneInfoList;
    }

    public void setZoneInfoList(List<KendraInfo> zoneInfoList) {
        this.zoneInfoList = zoneInfoList;
    }

    public List<Applications> getApplicationList() {
        if(applicationList == null){
            applicationList = new ArrayList<>();
        }
        return applicationList;
    }

    public void setApplicationList(List<Applications> applicationList) {
        this.applicationList = applicationList;
    }

    public AccessRole getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(AccessRole accessRole) {
        this.accessRole = accessRole;
    }

    @Override
    public String toString() {
        return "ACLInfo{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", zoneInfoList=" + zoneInfoList +
            ", kendraInfoList=" + kendraInfoList +
            ", applicationList=" + applicationList +
            ", accessRole=" + accessRole +
            '}';
    }

    public void populateUserInfo(User retUser) {
        setUserId(retUser.getId());
        setUserName(retUser.getUser_name());
    }
}
