package com.nermink.authorizationserver.domain.repository;

import com.nermink.authorizationserver.domain.model.ClientRedirectUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRedirectUrlRepository extends JpaRepository<ClientRedirectUrl, String > {

}
