package com.nermink.authorizationserver.domain.model;

import com.nermink.authorizationserver.dto.request.CreateClientRequest;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

  @Id
  private String id;
  private String secret;
  @ElementCollection(fetch = FetchType.EAGER)
  private Set<AuthorizationGrantType> grantTypes;
  private ClientAuthenticationMethod authenticationMethod;
  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<ClientRedirectUrl> redirectUris;
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "client_scope_mapping",
    joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "scope_id", referencedColumnName = "id")
  )
  private Collection<ClientScope> scopes;

  public Client(CreateClientRequest request) {
    this.id = request.getId();
    this.secret = request.getSecret();
    this.grantTypes = request.getGrantTypes();
    this.authenticationMethod = request.getAuthenticationMethod();
  }

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
