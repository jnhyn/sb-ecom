package com.ecommerce.project.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

  @GetMapping("/public/customer")
  public String getAllCustomer() {
    return "String";
  }
}
