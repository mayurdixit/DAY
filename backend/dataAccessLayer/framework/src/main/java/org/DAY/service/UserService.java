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
import java.util.List;
import java.util.Optional;

import org.DAY.db.entity.User;
import org.DAY.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 204048703 on 11/28/2017.
 */

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> userRecords = new ArrayList<>();
        userRepository.findAll().forEach(userRecords::add);
        return userRecords;
    }

    public Optional<User> getUser(String id){
        return userRepository.findById(id);
    }

    public void addUser(User userRecord){
        userRepository.save(userRecord);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
