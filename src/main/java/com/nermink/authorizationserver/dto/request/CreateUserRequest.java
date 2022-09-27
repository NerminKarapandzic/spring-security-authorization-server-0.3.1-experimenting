package com.nermink.authorizationserver.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
  private String username;
  private String password;
}
