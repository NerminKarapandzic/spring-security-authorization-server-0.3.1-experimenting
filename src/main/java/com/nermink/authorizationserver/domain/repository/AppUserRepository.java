package com.nermink.authorizationserver.domain.repository;

import com.nermink.authorizationserver.domain.model.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
  Optional<AppUser> findByUsername(String username);
}
