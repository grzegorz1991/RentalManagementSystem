package pl.grzegorz.rentalmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;
import pl.grzegorz.rentalmanagementsystem.service.EquipmentService;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private EquipmentService equipmentService;
    @GetMapping("/shop")
    public String home(Model model) {
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        model.addAttribute("equipmentList", equipmentList);
        return "shop";
    }


}
