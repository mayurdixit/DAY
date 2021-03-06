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

import org.DAY.db.entity.KendraInfo;
import org.DAY.service.KendraInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 204048703 on 12/4/2017.
 */

@RestController
public class KendraInfoController {

    @Autowired
    KendraInfoService kendraInfoService;

    @RequestMapping(value = "/internal/getAllKendra", method = RequestMethod.GET)
    public List<KendraInfo> getAllKendra() {
        return kendraInfoService.getAllKendra();
    }

    @RequestMapping(value = "/internal/getKendra/{id}", method = RequestMethod.POST)
    public Optional<KendraInfo> getKendra(@PathVariable String id){
        int kendraId = Integer.parseInt(id);
        return kendraInfoService.getKendraInfo(kendraId);
    }

    @RequestMapping(value = "/internal/getChildKendra/{id}", method = RequestMethod.POST)
    public List<KendraInfo> getChildKendra(@PathVariable String id){
        int kendraId = Integer.parseInt(id);
        return kendraInfoService.getChildKendraInfo(kendraId);
    }

}
