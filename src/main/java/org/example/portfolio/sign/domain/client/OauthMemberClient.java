package org.example.portfolio.sign.domain.client;

import org.example.portfolio.sign.domain.OauthServerType;
import org.example.portfolio.sign.domain.User;

public interface OauthMemberClient {

  OauthServerType supportServer();

  User fetch(String code);

}
