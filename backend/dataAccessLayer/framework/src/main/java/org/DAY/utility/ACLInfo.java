/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.utility;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private boolean resetPassword;
    private List<KendraInfo> zoneInfoList;
    private List<KendraInfo> kendraInfoList;
    private List<Applications> applicationList;
    private AccessRole accessRole;
    private String authToken;

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

    public boolean isResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String generateToken(){
        String generatedToken = null;
        Calendar calendar = new GregorianCalendar();
        Long miliSec = calendar.getTimeInMillis();
        int delayInMin = 30;

        String stringToken = miliSec + "#" + delayInMin;
        System.out.println("string token=" + stringToken);
        try {
            byte[] encodedToken = Base64.getEncoder().encode(stringToken.getBytes("utf-8"));
            generatedToken = new String(encodedToken, "utf-8");
            System.out.println("encoded token = " + generatedToken);
            byte[] decodedToken = Base64.getDecoder().decode(encodedToken);
            System.out.println("decoded token = " + new String(decodedToken, "utf-8"));

        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return generatedToken;
    }

    public String decodeToken(String encodedToken){
        String decodedStringToken = null;
        byte[] authToken = encodedToken.getBytes();
        byte[] decodedToken = Base64.getDecoder().decode(authToken);
        try {
            decodedStringToken = new String(decodedToken, "utf-8");
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return  decodedStringToken;
    }

    @Override
    public String toString() {
        return "ACLInfo{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", resetPassword=" + resetPassword +
            ", zoneInfoList=" + zoneInfoList +
            ", kendraInfoList=" + kendraInfoList +
            ", applicationList=" + applicationList +
            ", accessRole=" + accessRole +
            '}';
    }

    public void populateUserInfo(User retUser) {
        setUserId(retUser.getId());
        setUserName(retUser.getUser_name());
        setResetPassword(retUser.isPasswordReset());
    }


}
