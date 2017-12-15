/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.service;

import java.util.List;

import org.DAY.db.entity.UserAppRole;
import org.DAY.repository.IUserAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 204048703 on 12/15/2017.
 */
@Service
public class UserAppRoleService {
    @Autowired
    IUserAppRoleRepository userAppRoleRepository;

    public List<UserAppRole> getAppRoleByForUser(int userId){
        return userAppRoleRepository.findByUserId(userId);
    }
}
