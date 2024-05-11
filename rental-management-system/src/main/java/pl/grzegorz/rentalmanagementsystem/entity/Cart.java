package pl.grzegorz.rentalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id") // Define join column to establish the relationship with User entity
    private User user;

    public void addEquipment(Equipment equipment, int quantity) {
        // Check if the equipment is already in the cart
        for (CartItem item : cartItems) {
            if (item.getEquipment().equals(equipment)) {
                // Update the quantity if the equipment is already in the cart
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem newItem = new CartItem();
        newItem.setCart(this);
        newItem.setEquipment(equipment);
        newItem.setQuantity(quantity);

        cartItems.add(newItem);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartItems=" + cartItems +
                ", user=" + user +
                '}';
    }
}