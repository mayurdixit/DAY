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

import org.DAY.db.entity.UserAccessInfo;
import org.DAY.repository.IUserAccessInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 204048703 on 12/6/2017.
 */
@Service
public class UserAccessInfoService {
    @Autowired
    IUserAccessInfoRepository userAccessInfoRepository;

    public List<UserAccessInfo> getAllUserAccessInfo(){
        List<UserAccessInfo> allUserAccessInfo = new ArrayList<>();
        userAccessInfoRepository.findAll().forEach(allUserAccessInfo::add);
        return allUserAccessInfo;
    }

    public Optional<UserAccessInfo> getUserAccessInfo(String id) {
        int userAccessInfoId = Integer.parseInt(id);
        return userAccessInfoRepository.findById(userAccessInfoId);
    }

    public Optional<UserAccessInfo> getUserAccessInfoForUser(int userId){
        return userAccessInfoRepository.findByUserId(userId);
    }
}
