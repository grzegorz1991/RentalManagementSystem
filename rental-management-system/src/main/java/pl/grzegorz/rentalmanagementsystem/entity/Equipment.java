package pl.grzegorz.rentalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private int quantity;

    private double price;

}
