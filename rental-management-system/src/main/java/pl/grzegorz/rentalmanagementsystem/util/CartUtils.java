package pl.grzegorz.rentalmanagementsystem.util;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public class CartUtils {
    public static boolean isCartEmpty(HttpSession session) {
        List<Long> cart = (List<Long>) session.getAttribute("cartItems");
        return cart == null || cart.isEmpty();
    }

    public static int cartSize(HttpSession session){
        List<Long> cart = (List<Long>)session.getAttribute("cartItems");
        return  cart.size();

    }
}