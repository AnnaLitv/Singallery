package com.dipl.neuralphotos.rest;

import com.dipl.neuralphotos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ResponseEntity<?> findAllUsers(){
        try {
            return ResponseEntity.ok(userService.findAll());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.findUserById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/block/{id}",method = RequestMethod.POST)
    public ResponseEntity<?> blockUser(@PathVariable Long id){
        return null;
    }
}
