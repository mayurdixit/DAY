/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.utility;

import java.util.List;

import org.DAY.db.entity.KendraInfo;
import org.DAY.db.entity.User;

/**
 * Created by 204048703 on 12/8/2017.
 */
public class ACLInfo {
    private int userId;
    private String userName;
    private KendraInfo zoneInfo;
    private List<KendraInfo> kendraInfoList;

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

    public KendraInfo getZoneInfo() {
        return zoneInfo;
    }

    public void setZoneInfo(KendraInfo zoneInfo) {
        this.zoneInfo = zoneInfo;
    }

    public List<KendraInfo> getKendraInfoList() {
        return kendraInfoList;
    }

    public void setKendraInfoList(List<KendraInfo> kendraInfoList) {
        this.kendraInfoList = kendraInfoList;
    }

    @Override
    public String toString() {
        return "ACLInfo{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", zoneInfo=" + zoneInfo +
            ", kendraInfoList=" + kendraInfoList +
            '}';
    }

    public void populateUserInfo(User retUser) {
        setUserId(retUser.getId());
        setUserName(retUser.getUser_name());
    }
}
