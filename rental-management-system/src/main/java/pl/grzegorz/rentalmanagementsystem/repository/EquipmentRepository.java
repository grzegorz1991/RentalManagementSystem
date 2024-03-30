package pl.grzegorz.rentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;



public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
