package com.calendar.calendar.controller;

import com.calendar.calendar.model.User;
import com.calendar.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable(value = "id") Long id,@RequestBody User user){
        return ResponseEntity.ok(userService.updateUserFromId(id,user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long id){
        userService.deleteUserFromId(id);
        return ResponseEntity.ok().build();
    }
}
