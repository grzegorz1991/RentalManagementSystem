package pl.grzegorz.rentalmanagementsystem;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.grzegorz.rentalmanagementsystem.entity.Order;
import pl.grzegorz.rentalmanagementsystem.repository.OrderRepository;
import pl.grzegorz.rentalmanagementsystem.service.OrderService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testGetOrderById() {
        // Mocking data
        Order mockOrder = new Order();
        mockOrder.setId(1L);

        // Mocking repository behavior
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        // Call the service method
        Optional<Order> result = orderService.getOrderById(1L);

        // Verify the result
        assertEquals(1L, result.get().getId());
    }


}
