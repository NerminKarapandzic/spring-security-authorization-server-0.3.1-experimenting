package com.nermink.authorizationserver.dto.response;

import com.nermink.authorizationserver.domain.model.AppUser;
import com.nermink.authorizationserver.domain.model.Authority;
import java.util.Collection;
import java.util.List;
import lombok.Data;

@Data
public class UserResponse {
  private String username;
  private String password;
  private Collection<Authority> authorities;

  public UserResponse(AppUser user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authorities = (List<Authority>) user.getAuthorities();
  }
}
