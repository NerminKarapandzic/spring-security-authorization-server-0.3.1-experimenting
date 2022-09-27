package com.nermink.authorizationserver.service;

import com.nermink.authorizationserver.domain.model.AppUser;
import com.nermink.authorizationserver.domain.repository.AppUserRepository;
import com.nermink.authorizationserver.dto.request.CreateUserRequest;
import com.nermink.authorizationserver.dto.response.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final AppUserRepository appUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.appUserRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public UserResponse save(CreateUserRequest request) {
    var user = new AppUser(request.getUsername(), request.getPassword(), List.of());
    this.appUserRepository.save(user);
    return new UserResponse(user);
  }
}
