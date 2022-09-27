package com.nermink.authorizationserver.domain.model;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser implements UserDetails {

  @Id
  private String id;
  private String username;
  private String password;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_authority",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
  private Collection<Authority> authorities;
  private Boolean isAccountExpired = false;
  private Boolean isAccountLocked = false;
  private Boolean isCredentialsExpired = false;
  private Boolean isEnabled = true;

  public AppUser(String username, String password,
      Collection<Authority> authorities) {
    this.id = UUID.randomUUID().toString();
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !this.isAccountExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !this.isAccountLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !this.isCredentialsExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }
}
