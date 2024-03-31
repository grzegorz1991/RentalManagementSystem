package pl.grzegorz.rentalmanagementsystem;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.grzegorz.rentalmanagementsystem.controller.OrderController;
import pl.grzegorz.rentalmanagementsystem.entity.Order;
import pl.grzegorz.rentalmanagementsystem.service.OrderService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetOrderById() throws Exception {
        // Mock data
        Order mockOrder = new Order();
        mockOrder.setId(1L);

        // Mock service behavior
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(mockOrder));

        // Perform GET request and verify response
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    // Add more test methods for other controller functionalities
}