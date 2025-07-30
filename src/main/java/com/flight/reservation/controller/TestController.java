package com.flight.reservation.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/user/profile")
    public String profile() {
        return "Hello, this is your protected profile!";
    }
}

