package pl.grzegorz.rentalmanagementsystem;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.grzegorz.rentalmanagementsystem.controller.OrderController;
import pl.grzegorz.rentalmanagementsystem.entity.Order;
import pl.grzegorz.rentalmanagementsystem.service.OrderService;


import java.util.List;
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



    @Test
    public void testGetAllOrders() throws Exception {
        // Mock data
        Order mockOrder1 = new Order();
        mockOrder1.setId(1L);
        Order mockOrder2 = new Order();
        mockOrder2.setId(2L);

        // Mock service behavior
        when(orderService.getAllOrders()).thenReturn(List.of(mockOrder1, mockOrder2));

        // Perform GET request and verify response
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testGetOrderByIdNotFound() throws Exception {
        // Mock service behavior
        when(orderService.getOrderById(1L)).thenReturn(Optional.empty());

        // Perform GET request and verify response
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPlaceOrder() throws Exception {
        // Mock data
        Order mockOrder = new Order();
        mockOrder.setId(1L);

        // Mock service behavior
        when(orderService.placeOrder(any(Order.class))).thenReturn(mockOrder);

        // Perform POST request and verify response
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"user\":{\"id\":1},\"equipmentList\":[{\"id\":1},{\"id\":2}],\"orderDate\":\"2024-04-01\",\"returnDate\":\"2024-04-15\",\"status\":\"ACTIVE\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }


}