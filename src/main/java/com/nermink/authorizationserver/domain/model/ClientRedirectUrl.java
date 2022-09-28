package com.nermink.authorizationserver.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "client_redirect_uri")
public class ClientRedirectUrl {
  @Id
  private String id;
  private String url;
  @Setter
  @ManyToOne
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  public ClientRedirectUrl(String url, Client client) {
    this.id = RandomStringUtils.randomAlphanumeric(10);
    this.url = url;
    this.client = client;
  }
}
