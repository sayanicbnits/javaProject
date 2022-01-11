package com.java.springboot;



import org.springframework.web.bind.annotation.*;


@RestController
public class HelloWorld {

  @GetMapping("/hello")
    public String get(){
      return "Hello World";
  }

    }








