package com.nermink.authorizationserver.controller;

import com.nermink.authorizationserver.dto.request.CreateUserRequest;
import com.nermink.authorizationserver.dto.response.UserResponse;
import com.nermink.authorizationserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest req){
    var response = this.userService.save(req);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

}
