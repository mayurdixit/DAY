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

import org.DAY.db.entity.KendraInfo;
import org.DAY.repository.IKendraInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 204048703 on 12/4/2017.
 */

@Service
public class KendraInfoService {

    @Autowired
    IKendraInfoRepository kendraInfoRepository;

    public List<KendraInfo> getAllKendra(){
        List<KendraInfo> allKendraInfo = new ArrayList<>();
        kendraInfoRepository.findAll().forEach(allKendraInfo::add);
        return allKendraInfo;
    }

    public Optional<KendraInfo> getKendraInfo(String id){
        int kendraId = Integer.valueOf(id);
        return kendraInfoRepository.findById(kendraId);
    }

    public List<KendraInfo> getChildKendraInfo(String id){
        List<KendraInfo> childKendraInfo = new ArrayList<>();
        int kendraId = Integer.valueOf(id);
        kendraInfoRepository.findByParent(kendraId).forEach(childKendraInfo::add);
        return childKendraInfo;
    }
}
