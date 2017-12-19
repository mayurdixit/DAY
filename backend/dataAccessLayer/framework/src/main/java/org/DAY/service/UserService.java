/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.DAY.db.entity.User;
import org.DAY.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 204048703 on 11/28/2017.
 */

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers(){
        List<User> userRecords = new ArrayList<>();
        userRepository.findAll().forEach(userRecords::add);
        return userRecords;
    }

    public Optional<User> getUser(String id){
        int intId = Integer.parseInt(id);
        return userRepository.findById(intId);
    }

    public void addUser(User userRecord){
        userRepository.save(userRecord);
    }

    public void delete(String id) {
        System.out.println("id=" + id);
        int intId = Integer.parseInt(id);
        userRepository.deleteById(intId);
    }

    public Optional<User> getUserInfo(String userName){
        Optional<User> retOptional;
        System.out.println("userName= " + userName);
        User user = userRepository.findByUserName(userName);
        if(user != null) {
           retOptional = Optional.of(user);
        } else {
            retOptional = Optional.empty();
        }
        return retOptional;
    }

    public void updateLastAccessedOn(User currUser) {
        currUser.setLast_accessed_on(new Date());
        userRepository.save(currUser);
    }
}
