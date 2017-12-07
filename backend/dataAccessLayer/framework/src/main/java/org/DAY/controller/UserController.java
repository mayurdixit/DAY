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

import org.DAY.db.entity.KendraInfo;
import org.DAY.db.entity.User;
import org.DAY.db.entity.UserAccessInfo;
import org.DAY.service.KendraInfoService;
import org.DAY.service.UserAccessInfoService;
import org.DAY.service.UserService;
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
    private UserAccessInfoService userAccessInfoService;

    @Autowired
    private KendraInfoService kendraInfoService;

    @RequestMapping("/internal/user")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/add-user", method= RequestMethod.POST)
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
    public List<KendraInfo> authorizeUser(@RequestBody Login formData){
        User retUser;
        UserAccessInfo retUserAccessInfo;
        List<KendraInfo> retKendraInfo = new ArrayList<>();

        retUser = isUserHasAccess(formData);
        if(retUser != null){
            Optional userAccessInfoOprional = userAccessInfoService.getUserAccessInfoForUser(retUser.getId());
            if(userAccessInfoOprional.isPresent()){
                retUserAccessInfo = (UserAccessInfo)userAccessInfoOprional.get();
                int kendraId = retUserAccessInfo.getKendraId();
                List<KendraInfo> childKendraList = kendraInfoService.getChildKendraInfo(kendraId);
                if(childKendraList.size() > 0) {
                    retKendraInfo = childKendraList;
                } else {
                    retKendraInfo.add(getKendraInfo(kendraId));
                }
            }
        }
        return retKendraInfo;
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
