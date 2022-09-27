package com.nermink.authorizationserver.service;

import com.nermink.authorizationserver.domain.model.Client;
import com.nermink.authorizationserver.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

  private final ClientRepository clientRepository;

  @Override
  public void save(RegisteredClient registeredClient) {
  }

  @Override
  public RegisteredClient findById(String id) {
    var client = this.clientRepository.findById(id).orElseThrow();
    return Client.toRegisteredClient(client);
  }

  @Override
  public RegisteredClient findByClientId(String clientId) {
    return null;
  }
}
