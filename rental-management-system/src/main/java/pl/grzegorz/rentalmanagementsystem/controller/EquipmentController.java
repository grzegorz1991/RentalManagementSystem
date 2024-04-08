package pl.grzegorz.rentalmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;

import pl.grzegorz.rentalmanagementsystem.service.EquipmentService;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
        return equipment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
        Equipment addedEquipment = equipmentService.saveEquipment(equipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEquipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        Optional<Equipment> existingEquipment = equipmentService.getEquipmentById(id);
        if (existingEquipment.isEmpty()) {
            // Handle the case where equipment with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        Equipment equipmentToUpdate = existingEquipment.get();
        // Update the existing equipment with the data from the updated equipment
        equipmentToUpdate.setName(updatedEquipment.getName());
        equipmentToUpdate.setType(updatedEquipment.getType());
        equipmentToUpdate.setQuantity(updatedEquipment.getQuantity());
        equipmentToUpdate.setPrice(updatedEquipment.getPrice());

        // Save the updated equipment
        Equipment savedEquipment = equipmentService.saveEquipment(equipmentToUpdate);
        return ResponseEntity.ok(savedEquipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }
}
