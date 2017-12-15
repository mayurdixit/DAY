/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.DAY.db.entity.AccessRole;
import org.DAY.db.entity.Applications;
import org.DAY.db.entity.KendraInfo;
import org.DAY.db.entity.User;
import org.DAY.db.entity.UserAccessInfo;
import org.DAY.db.entity.UserAppRole;
import org.DAY.repository.IAccessRoleRepository;
import org.DAY.repository.IApplicationsRepository;
import org.DAY.service.KendraInfoService;
import org.DAY.service.UserAppRoleService;
import org.DAY.service.UserService;
import org.DAY.utility.ACLInfo;
import org.DAY.utility.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 204048703 on 11/28/2017.
 */

@RestController
public class UserController {
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

    @RequestMapping(value="/internal/authorize", method=RequestMethod.POST)
    public ACLInfo authorizeUser(@RequestBody Login formData){
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
            }
        return aclInfo;
        }



    private void updateKendraZoneList(ACLInfo aclInfo, List<Integer> addedKendraIdList, List<Integer>
        addedZoneIdList, int kendraId) {
        KendraInfo currKendraInfo = getKendraInfo(kendraId);
        List<KendraInfo> childKendraList = kendraInfoService.getChildKendraInfo(currKendraInfo.getId());
        if(childKendraList.size() > 0) {
            if(!addedZoneIdList.contains(currKendraInfo.getId())){
                aclInfo.getZoneInfoList().add(currKendraInfo);
                aclInfo.getKendraInfoList().addAll(childKendraList);
                addedZoneIdList.add(currKendraInfo.getId());
            }
        } else {
            if(!addedKendraIdList.contains(currKendraInfo.getId())){
                aclInfo.getKendraInfoList().add(currKendraInfo);
                Optional<KendraInfo> zoneKendraInfoOptional = kendraInfoService.getKendraInfo(currKendraInfo.getParent());
                if(zoneKendraInfoOptional.isPresent()){
                    aclInfo.getZoneInfoList().add(zoneKendraInfoOptional.get());
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
