package org.example.portfolio.sign.domain.authcode;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.example.portfolio.global.domain.ErrorCode;
import org.example.portfolio.global.exception.CustomException;
import org.example.portfolio.sign.domain.OauthServerType;
import org.springframework.stereotype.Component;

@Component
public class AuthCodeRequestUrlProviderComposite {

  private final Map<OauthServerType, AuthCodeRequestUrlProvider> mapping;

  public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
    mapping = providers.stream()
        .collect(Collectors.toMap(AuthCodeRequestUrlProvider::supportServer, Function.identity()));
  }

  public String provide(OauthServerType serverType) {
    return getProvider(serverType).provide();
  }

  private AuthCodeRequestUrlProvider getProvider(OauthServerType serverType) {
    return Optional.ofNullable(mapping.get(serverType)).orElseThrow(() -> new CustomException(
        ErrorCode.OAUTH_SERVER_TYPE_ERROR));
  }
}
