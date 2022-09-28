package com.nermink.authorizationserver.domain.repository;

import com.nermink.authorizationserver.domain.model.ClientScope;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientScopeRepository extends JpaRepository<ClientScope, String> {

}
