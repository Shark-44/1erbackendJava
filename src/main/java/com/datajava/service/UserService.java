package com.datajava.service;

import com.datajava.model.User;
import com.datajava.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.datajava.exception.UserAlreadyExistsException;


import java.util.Objects;

@Service
@Slf4j

public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User newUser) {
        if (userRepository.findUserWithName(newUser.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + newUser.getUsername() + " already exists.");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Objects.requireNonNull(username);
        User user = userRepository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user;
    }
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User updateUser(Integer id, User user) {
        // Trouvez l'utilisateur par ID
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        // Mettez à jour le nom d'utilisateur et le mot de passe si nécessaire
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    
        // Enregistrez les modifications
        return userRepository.save(existingUser);
    }
    
    
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    

}