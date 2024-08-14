package org.twspring.lab11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab11.Api.ApiResponse;
import org.twspring.lab11.Model.User;
import org.twspring.lab11.Service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));
    }
    //===========================PUT===========================
    @PutMapping("/update/{user_id}")
    public ResponseEntity updateUser(@PathVariable Integer user_id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        userService.updateUser(user_id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }
    //===========================DELETE===========================
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Integer user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }
}
