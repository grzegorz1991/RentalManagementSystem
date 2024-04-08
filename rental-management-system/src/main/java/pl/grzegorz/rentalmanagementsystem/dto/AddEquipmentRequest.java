package pl.grzegorz.rentalmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddEquipmentRequest {

    private Long equipmentId;
    private int quantity;

}
