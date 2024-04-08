package pl.grzegorz.rentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grzegorz.rentalmanagementsystem.entity.Cart;
import pl.grzegorz.rentalmanagementsystem.entity.Equipment;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
