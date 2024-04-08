package pl.grzegorz.rentalmanagementsystem.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.grzegorz.rentalmanagementsystem.entity.Cart;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;

@Service
public class CartService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cart addEquipmentToCart(Long cartId, Long equipmentId, int quantity) {
        Cart cart = entityManager.find(Cart.class, cartId);
        Equipment equipment = entityManager.find(Equipment.class, equipmentId);

        // Check if both cart and equipment exist
        if (cart != null && equipment != null) {
            // Add the equipment to the cart with the specified quantity
            cart.addEquipment(equipment, quantity);
        }

        return cart;
    }
}