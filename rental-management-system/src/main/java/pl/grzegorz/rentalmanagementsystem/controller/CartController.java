package pl.grzegorz.rentalmanagementsystem.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.grzegorz.rentalmanagementsystem.dto.AddEquipmentRequest;

//import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/cart")
public class CartController {


    @PostMapping("/{cartId}/addEquipment")
    public ResponseEntity<String> addEquipmentToCart(
            @PathVariable Long cartId,
            @RequestBody AddEquipmentRequest request, HttpSession session
    ) {
        try {
            Long equipmentId = request.getEquipmentId();
            // Log the equipment ID to console or log files
            System.out.println("Received equipment ID: " + equipmentId);

            // Get the cart items from session
            List<Long> cart = (List<Long>) session.getAttribute("cartItems");
            if (cart == null) {
                System.out.println("Initialising cart");
                cart = new ArrayList<>();
                session.setAttribute("cartItems", cart);
            }

            // Add the equipment ID to the cart
            cart.add(equipmentId);
            session.setAttribute("cartItems", cart);

            return ResponseEntity.ok("Equipment added to cart successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    @GetMapping("/viewCart")
    public ResponseEntity<?> viewCart(HttpServletRequest httpServletRequest) {
        try {
            HttpSession session = httpServletRequest.getSession();
            List<Long> cart = (List<Long>) session.getAttribute("cartItems");
            if (cart != null && !cart.isEmpty()) {
                return ResponseEntity.ok("Cart Contents: " + cart.toString());
            } else {
                return ResponseEntity.ok("Cart is Empty");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/clearCart")
    public ResponseEntity<String> clearCart(HttpServletRequest httpServletRequest) {
        try {
            HttpSession session = httpServletRequest.getSession();
            session.removeAttribute("cartItems");
            return ResponseEntity.ok("Cart Cleared");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
