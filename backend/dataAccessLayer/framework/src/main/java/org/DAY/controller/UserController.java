/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.controller;

import java.util.List;
import java.util.Optional;

import org.DAY.db.entity.UserEntity;
import org.DAY.service.UserService;
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

    @RequestMapping("/user")
    public List<UserEntity> getAllUser(){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/add-user", method= RequestMethod.POST)
    public void addUser(@RequestBody UserEntity userRecord){
        userService.addUser(userRecord);
    }

    @RequestMapping(value="/user/{id}", method=RequestMethod.GET)
    public Optional<UserEntity> getUser(@PathVariable String id){
        return userService.getUser(id);
    }
}
