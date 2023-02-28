package com.alibou.security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/admin")
  public ResponseEntity<String> sayHello1() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }
  @PreAuthorize("hasAuthority('USER')")
  @GetMapping("/user")
  public ResponseEntity<String> sayHello2() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }


}
