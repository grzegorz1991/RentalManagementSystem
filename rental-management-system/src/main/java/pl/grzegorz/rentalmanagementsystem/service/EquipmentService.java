package pl.grzegorz.rentalmanagementsystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;
import pl.grzegorz.rentalmanagementsystem.repository.EquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService { 
    @Autowired
    private EquipmentRepository equipmentRepository;


    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }


    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }
    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}
