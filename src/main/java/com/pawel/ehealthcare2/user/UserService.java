package com.pawel.ehealthcare2.user;

import com.pawel.ehealthcare2.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!userRepository.existsByUsername(username)){
            throw new UsernameNotFoundException("User '" + username + " does not exist!");
        }
        return userRepository.findByUsername(username);

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user) throws UserAlreadyExistsException {
        String username = user.getUsername();
        if(userRepository.existsByUsername(username)){
            throw new UserAlreadyExistsException("User '" + username + "' already exists!");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.save(user);

    }
}
