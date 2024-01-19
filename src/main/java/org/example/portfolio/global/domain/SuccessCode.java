package org.example.portfolio.global.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessCode {

  SUCCESS(HttpStatus.OK.value(), "SUCCESS"),
  CREATED(HttpStatus.CREATED.value(), "CREATED");

  private final int status;
  private final String message;
}
