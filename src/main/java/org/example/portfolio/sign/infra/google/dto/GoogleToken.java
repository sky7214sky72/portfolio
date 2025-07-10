package org.example.portfolio.sign.infra.google.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record GoogleToken(
    String accessToken,
    String idToken,
    String refreshToken,
    Integer expiresIn,
    String scope,
    String tokenType
) {}
