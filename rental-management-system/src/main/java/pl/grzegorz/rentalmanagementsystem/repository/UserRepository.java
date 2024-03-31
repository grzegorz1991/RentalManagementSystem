package pl.grzegorz.rentalmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grzegorz.rentalmanagementsystem.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}

