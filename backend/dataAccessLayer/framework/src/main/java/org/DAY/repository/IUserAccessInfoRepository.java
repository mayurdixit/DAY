/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.repository;

import java.util.Optional;

import org.DAY.db.entity.UserAccessInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 204048703 on 12/6/2017.
 */
@Repository
public interface IUserAccessInfoRepository extends CrudRepository<UserAccessInfo, Integer>{
    @Query("SELECT ua FROM UserAccessInfo ua WHERE ua.userId = :id")
    Optional<UserAccessInfo> findByUserId(int id);
}
