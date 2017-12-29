package org.DAY.service;

import org.DAY.db.entity.AccessRole;
import org.DAY.repository.IAccessRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessRoleService {

    @Autowired
    private IAccessRoleRepository accessRoleRepository;

    public Optional<AccessRole> getAccessRoleById(int id){
        return accessRoleRepository.findById(id);
    }
}
