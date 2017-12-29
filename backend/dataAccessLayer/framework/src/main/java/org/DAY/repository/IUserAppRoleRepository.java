/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.repository;

import java.util.List;

import org.DAY.db.entity.UserAppRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 204048703 on 12/15/2017.
 */
@Repository
public interface IUserAppRoleRepository extends CrudRepository<UserAppRole, Integer>{

    @Query("SELECT uar from UserAppRole uar where uar.userId = :id")
    List<UserAppRole> findByUserId(int id);

    @Query("SELECT uar from UserAppRole uar where uar.appId = :id")
    List<UserAppRole> getAppRoleByApp(int id);
}
