package com.pawel.ehealthcare2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
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
