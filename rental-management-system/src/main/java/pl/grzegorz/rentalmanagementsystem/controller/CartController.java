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
import pl.grzegorz.rentalmanagementsystem.entity.CartItem;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;
import pl.grzegorz.rentalmanagementsystem.service.EquipmentService;
import pl.grzegorz.rentalmanagementsystem.service.NewsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/cart")
public class CartController {

    private final EquipmentService equipmentService;

    public CartController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }


    @PostMapping("/{cartId}/addEquipment")
    public ResponseEntity<String> addEquipmentToCart(
            @PathVariable Long cartId,
            @RequestBody AddEquipmentRequest request, HttpSession session
    ) {
        try {
            Long equipmentId = request.getEquipmentId();
            LocalDate startDate = request.getStartDate(); // Retrieve start date from the request
            LocalDate endDate = request.getEndDate(); // Retrieve end date from the request
            int quantity = request.getQuantity(); // Retrieve quantity from the request

            System.out.println("CART ITEM ALMOST ADDED");



            Equipment equipment = equipmentService.getEquipmentById(equipmentId).orElse(null);
            if (equipment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not found");
            }

            // Get the cart items from session
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cartItems");
            if (cart == null) {
                System.out.println("Initializing cart");
                cart = new ArrayList<>();
                session.setAttribute("cartItems", cart);
            }

            // Add the Equipment object, dates, and quantity to the cart
            cart.add(new CartItem(equipment, startDate, endDate, quantity));
            System.out.println(cart);
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
