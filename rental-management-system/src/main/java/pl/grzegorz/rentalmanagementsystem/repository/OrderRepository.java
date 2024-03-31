package pl.grzegorz.rentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grzegorz.rentalmanagementsystem.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
