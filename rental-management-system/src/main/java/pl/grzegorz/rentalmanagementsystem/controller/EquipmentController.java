package pl.grzegorz.rentalmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;
import pl.grzegorz.rentalmanagementsystem.exception.ResourceNotFoundException;
import pl.grzegorz.rentalmanagementsystem.service.EquipmentService;

import java.util.List;


@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    @PostMapping
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }

    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        Equipment existingEquipment = equipmentService.getEquipmentById(id);
        if (existingEquipment == null) {
            // Handle the case where equipment with the given ID is not found
            throw new ResourceNotFoundException("Equipment not found with id: " + id);
        }
        // Update the existing equipment with the data from the updated equipment
        existingEquipment.setName(updatedEquipment.getName());
        existingEquipment.setType(updatedEquipment.getType());
        existingEquipment.setQuantity(updatedEquipment.getQuantity());
        existingEquipment.setPrice(updatedEquipment.getPrice());

        // Save the updated equipment
        return equipmentService.saveEquipment(existingEquipment);
    }
    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }
}
