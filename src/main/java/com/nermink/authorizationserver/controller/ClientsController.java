package com.nermink.authorizationserver.controller;

import com.nermink.authorizationserver.dto.request.CreateClientRequest;
import com.nermink.authorizationserver.dto.response.ClientResponse;
import com.nermink.authorizationserver.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {

  private final ClientService clientService;

  @PostMapping
  public ResponseEntity<ClientResponse> createClient(@RequestBody CreateClientRequest request) {
    var response = clientService.createClient(request);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

}
