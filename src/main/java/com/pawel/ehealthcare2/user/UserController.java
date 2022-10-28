package com.pawel.ehealthcare2.user;

import com.pawel.ehealthcare2.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String home(){
        return("<h1>HOME</h1>");
    }

    @GetMapping("/user")
    public String welcomeUser(@CurrentSecurityContext(expression = "authentication.name")
                              String username){
        return ("<h1>Welcome " + username + "</h1>");
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user, BindingResult bindingResult){
        try{
            userService.addUser(user);
        } catch (UserAlreadyExistsException e){
            return ("User already exists!");
        }
        return "redirect:user";
    }

    @GetMapping("/admin")
    public String welcomeAdmin(){
        return("<h1>Welcome admin</h1>");
    }

    // this mapping is for test purpose only, check data loading of all users
    @GetMapping("/admin/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
