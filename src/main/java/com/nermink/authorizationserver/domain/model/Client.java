package com.nermink.authorizationserver.domain.model;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

@Entity
@Getter
@NoArgsConstructor
public class Client {

  @Id
  private String id;
  private String secret;
  @ElementCollection
  private Set<AuthorizationGrantType> grantTypes;
  private ClientAuthenticationMethod authenticationMethod;
  @OneToMany(mappedBy = "client")
  private Set<ClientRedirectUrl> redirectUris;
  @ManyToMany()
  @JoinTable(name = "client_scope_mapping",
    joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "scope_id", referencedColumnName = "id")
  )
  private Collection<ClientScope> scopes;

  public static RegisteredClient toRegisteredClient(Client client) {
    RegisteredClient.Builder builder = RegisteredClient.withId(client.getId())
      .clientId(client.getId())
      .clientSecret(client.getSecret())
      .clientAuthenticationMethod(client.getAuthenticationMethod())
      .authorizationGrantTypes(
          authorizationGrantTypes -> authorizationGrantTypes.addAll(client.getGrantTypes()))
      .redirectUris(
          redirectUris -> redirectUris.addAll(client.getRedirectUris()
              .stream()
              .map(ClientRedirectUrl::getUrl)
              .collect(Collectors.toSet())))
      .scopes(scopes -> scopes.addAll(client.getScopes()
          .stream()
          .map(ClientScope::getScope)
          .collect(Collectors.toSet())));
    return builder.build();
  }

}
