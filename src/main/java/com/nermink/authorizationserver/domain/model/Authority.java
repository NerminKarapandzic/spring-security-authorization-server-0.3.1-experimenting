package com.nermink.authorizationserver.domain.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Authority implements GrantedAuthority {

  @Id
  private String id;
  private String name;

  public Authority(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
  }


  @Override
  public String getAuthority() {
    return this.name;
  }
}
