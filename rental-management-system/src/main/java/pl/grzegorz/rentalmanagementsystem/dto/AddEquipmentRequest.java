package pl.grzegorz.rentalmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AddEquipmentRequest {

    private Long equipmentId;
    private LocalDate startDate; // Add start date field
    private LocalDate endDate; // Add end date field
    private int quantity;

}
