package com.lshaci.spring_cloud_gateway.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @GetMapping("login")
    public String login() {
        return "md5-string";
    }
}
