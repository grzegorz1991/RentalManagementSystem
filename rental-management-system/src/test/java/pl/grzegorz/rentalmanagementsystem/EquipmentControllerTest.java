package pl.grzegorz.rentalmanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.grzegorz.rentalmanagementsystem.controller.EquipmentController;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;
import pl.grzegorz.rentalmanagementsystem.service.EquipmentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.http.MediaType;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentService equipmentService;

    @Test
    public void testGetAllEquipment() throws Exception {
        // Mock data
        Equipment equipment1 = new Equipment();
        equipment1.setId(1L);
        Equipment equipment2 = new Equipment();
        equipment2.setId(2L);

        // Mock service behavior
        when(equipmentService.getAllEquipment()).thenReturn(List.of(equipment1, equipment2));

        // Perform GET request and verify response
        mockMvc.perform(get("/api/equipment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testGetEquipmentById() throws Exception {
        // Mock data
        Equipment equipment = new Equipment();
        equipment.setId(1L);

        // Mock service behavior
        when(equipmentService.getEquipmentById(1L)).thenReturn(Optional.of(equipment));

        // Perform GET request and verify response
        mockMvc.perform(get("/api/equipment/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetEquipmentByIdNotFound() throws Exception {
        // Mock service behavior
        when(equipmentService.getEquipmentById(1L)).thenReturn(Optional.empty());

        // Perform GET request and verify response
        mockMvc.perform(get("/api/equipment/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddEquipment() throws Exception {
        // Mock data
        Equipment equipment = new Equipment();
        equipment.setId(1L);

        // Mock service behavior
        when(equipmentService.saveEquipment(any(Equipment.class))).thenReturn(equipment);

        // Perform POST request and verify response
        mockMvc.perform(post("/api/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Equipment 1\",\"type\":\"Type 1\",\"quantity\":5,\"price\":100.0}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }
}