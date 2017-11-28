/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY.repository;

import org.DAY.db.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 204048703 on 11/28/2017.
 */
public interface IUserRepository extends CrudRepository<UserEntity, String>{
}
