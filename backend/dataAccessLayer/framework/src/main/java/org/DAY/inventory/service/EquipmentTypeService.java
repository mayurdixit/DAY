package org.DAY.inventory.service;

import org.DAY.inventory.entity.EquipmentType;
import org.DAY.inventory.repository.IEquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentTypeService {

    @Autowired
    private IEquipmentTypeRepository equipmentTypeRepository;

    public Optional<EquipmentType> getEquipmentType(int id){
        return equipmentTypeRepository.findById(id);
    }

    public List<EquipmentType> getAllEquipmentType(){
        List<EquipmentType> allEquipmentTypes = new ArrayList<>();
        equipmentTypeRepository.findAll().forEach(allEquipmentTypes::add);
        return allEquipmentTypes;
    }
}
