package pl.grzegorz.rentalmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import pl.grzegorz.rentalmanagementsystem.entity.User;
import pl.grzegorz.rentalmanagementsystem.repository.UserRepository;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id){ return userRepository.findById(id);}

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long id, User updatedUser) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());

            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }}

        public void deleteUser(Long id) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                userRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("User with ID " + id + " not found");
            }
        }
}
