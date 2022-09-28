package com.nermink.authorizationserver.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "client_scope")
public class ClientScope {

  @Id
  private String id;
  private String scope;

  public ClientScope(String scope) {
    this.id = RandomStringUtils.randomAlphanumeric(10);
    this.scope = scope;
  }
}
