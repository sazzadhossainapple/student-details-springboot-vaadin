package com.example.springbootdemoproject3.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hello")
public class helloController {
    @GetMapping(value = "")

    public String sayHello()
    {
        return "Hello";
    }

    @GetMapping(value = "/hi")
    public String sayHi()
    {
        return "Hi";
    }

    @GetMapping(value = "/hi/{name}")

    public String sayHi(@PathVariable String name)
    {
        return "Hi, " +name;
    }


}