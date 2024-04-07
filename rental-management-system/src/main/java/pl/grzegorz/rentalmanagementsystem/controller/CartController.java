package pl.grzegorz.rentalmanagementsystem.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.grzegorz.rentalmanagementsystem.dto.AddEquipmentRequest;
import pl.grzegorz.rentalmanagementsystem.entity.Cart;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;
import pl.grzegorz.rentalmanagementsystem.exception.CartNotFoundException;
import pl.grzegorz.rentalmanagementsystem.exception.EquipmentNotFoundException;
import pl.grzegorz.rentalmanagementsystem.exception.InvalidQuantityException;
import pl.grzegorz.rentalmanagementsystem.repository.CartRepository;
import pl.grzegorz.rentalmanagementsystem.repository.EquipmentRepository;
import pl.grzegorz.rentalmanagementsystem.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/addEquipment")
    public ResponseEntity<String> addEquipmentToCart(@PathVariable Long cartId, @RequestBody AddEquipmentRequest request) {
        try {
            cartService.addEquipmentToCart(cartId, request.getEquipmentId(), request.getQuantity());
            return ResponseEntity.ok("Equipment added to cart successfully");
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not found");
        } catch (InvalidQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}