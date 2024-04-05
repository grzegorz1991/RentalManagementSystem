package pl.grzegorz.rentalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    public enum EquipmentType {
        stage,
        lights,
        sound,

        other,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    private int quantity;

    private double price;

    private String imageUrl;

}
