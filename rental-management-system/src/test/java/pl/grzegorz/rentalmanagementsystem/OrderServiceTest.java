package pl.grzegorz.rentalmanagementsystem;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.grzegorz.rentalmanagementsystem.entity.Order;
import pl.grzegorz.rentalmanagementsystem.repository.OrderRepository;
import pl.grzegorz.rentalmanagementsystem.service.OrderService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testGetAllOrders() {
        // Mock data
        Order order1 = new Order();
        Order order2 = new Order();
        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));

        // Call service method
        List<Order> result = orderService.getAllOrders();

        // Verify result
        assertEquals(2, result.size());
    }

    @Test
    public void testGetOrderById() {
        // Mock data
        Order mockOrder = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        // Call service method
        Optional<Order> result = orderService.getOrderById(1L);

        // Verify result
        assertEquals(mockOrder, result.orElse(null));
    }

    @Test
    public void testPlaceOrder() {
        // Mock data
        Order order = new Order();
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Call service method
        Order result = orderService.placeOrder(new Order());

        // Verify result
        assertEquals(order, result);
    }
}
