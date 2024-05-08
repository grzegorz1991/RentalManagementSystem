package pl.grzegorz.rentalmanagementsystem.aspect;

import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.grzegorz.rentalmanagementsystem.util.CartUtils;

@Aspect
@Component
public class CartAspect {

    @Autowired
    private HttpSession session;

    @Before("execution(* pl.grzegorz.rentalmanagementsystem.controller.*.*(..))")
    public void beforeControllerExecution() {
        boolean isCartEmpty = CartUtils.isCartEmpty(session);

        // Get the Model from the request attributes
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Model model = (Model) attr.getAttribute("org.springframework.web.servlet.HandlerMapping.uriTemplateVariables", ServletRequestAttributes.SCOPE_REQUEST);

        // Add the attribute to the model
        assert model != null;
        model.addAttribute("isCartEmpty", isCartEmpty);
    }
}