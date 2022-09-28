package com.nermink.authorizationserver.dto.request;

import java.util.Collection;
import java.util.Set;
import lombok.Data;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Data
public class CreateClientRequest {
  private String id;
  private String secret;
  private Set<AuthorizationGrantType> grantTypes;
  private ClientAuthenticationMethod authenticationMethod;
  private Set<String> redirectUris;
  private Collection<String> scopes;
}
