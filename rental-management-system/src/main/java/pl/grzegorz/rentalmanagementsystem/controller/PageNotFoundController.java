package pl.grzegorz.rentalmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageNotFoundController extends BaseController{


    @GetMapping("/error404")
    public String pageNotFound() {

        return "404";
    }
}
