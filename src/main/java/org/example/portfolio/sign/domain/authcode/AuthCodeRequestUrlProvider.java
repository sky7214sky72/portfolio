package org.example.portfolio.sign.domain.authcode;

import org.example.portfolio.sign.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

  OauthServerType supportServer();
  String provide();
}
