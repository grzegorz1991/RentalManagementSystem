package pl.grzegorz.rentalmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    private int quantity;


    private LocalDate startDate; // Add start date field

    private LocalDate endDate; // Add end date field


    public CartItem(Equipment equipment, LocalDate startDate, LocalDate endDate, int quantity) {
        this.equipment = equipment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
    }

}
