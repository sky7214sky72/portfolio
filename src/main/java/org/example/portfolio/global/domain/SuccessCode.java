package org.example.portfolio.global.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessCode {

  SUCCESS(HttpStatus.OK, "SUCCESS"),
  CREATED(HttpStatus.CREATED, "CREATED");

  private final HttpStatus status;
  private final String message;
}
