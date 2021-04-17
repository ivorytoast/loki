package com.titan.loki.controller;

import com.titan.loki.database.Vision;
import com.titan.loki.model.User;
import com.titan.loki.model.UserLoginRequest;
import com.titan.loki.model.UserLoginResponse;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@CrossOrigin
@RestController
@RequestMapping("vision")
public class VisionController {

    private final Vision service;

    public VisionController(Vision service) {
        this.service = service;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "UP";
    }

    @GetMapping("/v1/users/all")
    public List<User> getAllUsersInDatabase() {
        return service.getAllUsersInDatabase();
    }

    @GetMapping("/v1/users/validate/{userID}")
    public boolean isUserInDatabase(@PathVariable String userID) {
        return service.isUserInDatabase(userID);
    }

    @PostMapping("/v1/users/validate/login")
    public UserLoginResponse isCorrectLogin(@RequestBody UserLoginRequest userLoginRequest) {
        return new UserLoginResponse(service.isCorrectLogin(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
    }

    @PostMapping("/v1/users/create")
    public boolean createNewUser(@RequestBody UserLoginRequest userLoginRequest) {
        return service.createNewUser(userLoginRequest);
    }

}
