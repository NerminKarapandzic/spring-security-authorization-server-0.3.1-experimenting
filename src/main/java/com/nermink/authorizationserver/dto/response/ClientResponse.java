package com.nermink.authorizationserver.dto.response;

import com.nermink.authorizationserver.domain.model.Client;
import com.nermink.authorizationserver.domain.model.ClientRedirectUrl;
import com.nermink.authorizationserver.domain.model.ClientScope;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Data
public class ClientResponse {
  private String id;
  private String secret;
  private Set<AuthorizationGrantType> grantTypes;
  private ClientAuthenticationMethod authenticationMethod;
  private Set<String> redirectUris;
  private Collection<String> scopes;

  public ClientResponse(Client client) {
    this.id = client.getId();
    this.secret = client.getSecret();
    this.grantTypes = client.getGrantTypes();
    this.authenticationMethod = client.getAuthenticationMethod();
    this.redirectUris = client.getRedirectUris().stream()
        .map(ClientRedirectUrl::getUrl)
        .collect(Collectors.toSet());
    this.scopes = client.getScopes().stream()
        .map(ClientScope::getScope)
        .collect(Collectors.toSet());
  }
}
