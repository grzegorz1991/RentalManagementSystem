package pl.grzegorz.rentalmanagementsystem.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.grzegorz.rentalmanagementsystem.util.CartUtils;
@Component
public class BaseController {

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        boolean isCartEmpty = CartUtils.isCartEmpty(session);
        model.addAttribute("isCartEmpty", isCartEmpty);

    }
}