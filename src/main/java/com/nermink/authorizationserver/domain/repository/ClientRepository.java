package com.nermink.authorizationserver.domain.repository;

import com.nermink.authorizationserver.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

}
