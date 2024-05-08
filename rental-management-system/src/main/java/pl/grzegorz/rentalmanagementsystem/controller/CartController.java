package pl.grzegorz.rentalmanagementsystem.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
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
    public RedirectView clearCart(HttpServletRequest httpServletRequest, RedirectAttributes attributes) {
        try {
            HttpSession session = httpServletRequest.getSession();
            session.removeAttribute("cartItems");

            // Add a flash attribute to indicate successful cart clearance
            attributes.addFlashAttribute("cartCleared", true);

            // Redirect to the desired page
            return new RedirectView("/"); // Replace "/your-page-url" with the actual URL
        } catch (Exception e) {
            // Handle exceptions if needed
            return new RedirectView("/error-page"); // Redirect to an error page
        }
    }
}
