package pl.grzegorz.rentalmanagementsystem.aspect;

import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* pl.grzegorz.rentalmanagementsystem.controller.*.*(..))")
    public void beforeControllerExecution() {
        boolean isCartEmpty = CartUtils.isCartEmpty(session);
        request.setAttribute("isCartEmpty", isCartEmpty);
    }
}