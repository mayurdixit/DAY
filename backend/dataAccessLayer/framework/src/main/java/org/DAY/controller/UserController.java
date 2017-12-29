/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.controller;

import java.util.*;

import org.DAY.db.entity.AccessRole;
import org.DAY.db.entity.Applications;
import org.DAY.db.entity.KendraInfo;
import org.DAY.db.entity.User;
import org.DAY.db.entity.UserAccessInfo;
import org.DAY.db.entity.UserAppRole;
import org.DAY.utility.AppUserData;
import org.DAY.inventory.utility.UsersByAppParam;
import org.DAY.repository.IAccessRoleRepository;
import org.DAY.repository.IApplicationsRepository;
import org.DAY.service.AccessRoleService;
import org.DAY.service.KendraInfoService;
import org.DAY.service.UserAppRoleService;
import org.DAY.service.UserService;
import org.DAY.utility.ACLInfo;
import org.DAY.utility.IConstants;
import org.DAY.utility.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 204048703 on 11/28/2017.
 */

@RestController
public class UserController implements IConstants{
    @Autowired
    private UserService userService;

    @Autowired
    private UserAppRoleService userAppRoleService;

    @Autowired
    private KendraInfoService kendraInfoService;

    @Autowired
    private IApplicationsRepository applicationsRepository;

    @Autowired
    private IAccessRoleRepository accessRoleRepository;

    @Autowired
    private AccessRoleService accessRoleService;

    @RequestMapping("/internal/user")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/internal/add-user", method= RequestMethod.POST)
    public void addUser(@RequestBody User userRecord){
        userService.addUser(userRecord);
    }

    @RequestMapping(value="/internal/user/{id}", method=RequestMethod.GET)
    public Optional<User> getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @RequestMapping(value="/delete-user/{id}", method=RequestMethod.POST)
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

    @RequestMapping(value="/internal/user-by-app", method=RequestMethod.POST)
    public List<AppUserData> getUserByApp(@RequestBody UsersByAppParam param){
        System.out.println("User-by-app AppId=" + param.getAppId() + " ZoneId=" + param.getZoneId());
        Map<Integer, String> zoneNameIdMap = new HashMap<>();
        Map<Integer, String> roleNameIdMap = new HashMap<>();
        List<Integer> allKendraIds = new ArrayList<>();
        List<Integer> allUserIds = new ArrayList<>();
        List<AppUserData> listOfAppRoleUserData = new ArrayList<>();

        //get all child kendra for given zoneId
        List<KendraInfo> allKendraInfo = kendraInfoService.getChildKendraInfo(param.getZoneId());
        allKendraIds.add(param.getZoneId());
        allKendraInfo.forEach(kendraInfo -> {
            allKendraIds.add(kendraInfo.getId());
        });

        //get all userAppRole for give App
        List<UserAppRole> allUserRoleForApp = userAppRoleService.getAppRoleByApp(param.getAppId());
        allUserRoleForApp.forEach(userAppRole -> {
            if(allKendraIds.contains(userAppRole.getKendraId())) {
                AppUserData appUserData = new AppUserData();
                //add User
                Optional<User> userOptional = getUser(String.valueOf(userAppRole.getUserId()));
                if(userOptional.isPresent()){
                    appUserData.setUser(userOptional.get());
                }
                //add AccessRole info
                Optional<AccessRole> accessRoleOptional = accessRoleService.getAccessRoleById(userAppRole.getRoleId());
                if(accessRoleOptional.isPresent()){
                    appUserData.setRole(accessRoleOptional.get());
                }
                //add zone info
                Optional<KendraInfo> kendraInfoOptional = kendraInfoService.getKendraInfo(userAppRole.getKendraId());
                if(kendraInfoOptional.isPresent()){
                    appUserData.setKendra(kendraInfoOptional.get());
                }
                listOfAppRoleUserData.add(appUserData);
            }
        });
        return listOfAppRoleUserData;
    }

    @RequestMapping(value="/internal/authorize", method=RequestMethod.POST)
    public ResponseEntity<?> authorizeUser(@RequestBody Login formData){
        User retUser;
        ACLInfo aclInfo = new ACLInfo();
        UserAccessInfo retUserAccessInfo;
        List<KendraInfo> retKendraInfo = new ArrayList<>();
        List<KendraInfo> retZoneInfo = new ArrayList<>();
        int parentKendraId;
        List<Integer> addedKendraIdList = new ArrayList<>();
        List<Integer> addedZoneIdList = new ArrayList<>();


        retUser = isUserHasAccess(formData);
        if(retUser != null){
            System.out.println("User Has Access");
            aclInfo.populateUserInfo(retUser);
            List<UserAppRole> userAppRoleList = userAppRoleService.getAppRoleByForUser(retUser.getId());
                userAppRoleList.forEach(userAppRole -> {
                    int kendraId = userAppRole.getKendraId();
                    //add Kendras and Zones
                    updateKendraZoneList(aclInfo, addedKendraIdList, addedZoneIdList, kendraId);
                    //add applications
                    Optional<Applications> applicationsOptional = applicationsRepository.findById(userAppRole.getAppId());
                    if(applicationsOptional.isPresent()){
                        aclInfo.getApplicationList().add(applicationsOptional.get());
                    }
                    //add roles
                    Optional<AccessRole> accessRoleOptional = accessRoleRepository.findById(userAppRole.getRoleId());
                    if(accessRoleOptional.isPresent()){
                        aclInfo.setAccessRole(accessRoleOptional.get());
                    }
                });
                userService.updateLastAccessedOn(retUser);

            } else {
                return new ResponseEntity<String>("Username or Password is incorrect", HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity(aclInfo, HttpStatus.OK);
        }

    private void updateKendraZoneList(ACLInfo aclInfo, List<Integer> addedKendraIdList, List<Integer>
        addedZoneIdList, int kendraId) {
        System.out.println("called updateKendraZoneList with kendraID = " + kendraId);
        KendraInfo currKendraInfo = getKendraInfo(kendraId);
        List<KendraInfo> childKendraList = kendraInfoService.getChildKendraInfo(currKendraInfo.getId());

        if(childKendraList.size() > 0) {
            System.out.println("It's a zone");
            aclInfo.getZoneInfoList().add(currKendraInfo);
            addedZoneIdList.add(currKendraInfo.getId());
            System.out.println("after adding zoneIdList = " + addedZoneIdList.toString());
            childKendraList.forEach(childKendra ->{
                if(childKendra.getId() != MADHAVKENDRA) {
                    updateKendraZoneList(aclInfo, addedKendraIdList, addedZoneIdList, childKendra.getId());
                }
            });
        } else {
            System.out.println("It's kendra");
            if(!addedKendraIdList.contains(currKendraInfo.getId())){
                aclInfo.getKendraInfoList().add(currKendraInfo);
                if(!addedZoneIdList.contains(currKendraInfo.getParent())) {
                    Optional<KendraInfo> zoneKendraInfoOptional = kendraInfoService.getKendraInfo(currKendraInfo.getParent());
                    if (zoneKendraInfoOptional.isPresent()) {
                        aclInfo.getZoneInfoList().add(zoneKendraInfoOptional.get());
                    }
                }
                addedKendraIdList.add(currKendraInfo.getId());
            }
        }
    }


    private User isUserHasAccess(Login data){
        User validUser = null;
        System.out.println("data=" + data);
        Optional optional = userService.getUserInfo(data.getUserName());
        if(optional.isPresent()){
            validUser = (User)optional.get();
            if(!data.getPassword().equals(validUser.getPassword())) {
                validUser = null;
            }
        } else {
            System.out.println("User Not found");
        }
        return validUser;
    }

    private KendraInfo getKendraInfo(int kendraId) {
        KendraInfo kendraInfo=null;
        Optional kendraInfoOptional = kendraInfoService.getKendraInfo(kendraId);
        if (kendraInfoOptional.isPresent()){
            kendraInfo = (KendraInfo) kendraInfoOptional.get();
        }
        return kendraInfo;
    }
}
