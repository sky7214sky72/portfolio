package org.example.portfolio.global.exception;

import lombok.Getter;
import org.example.portfolio.global.domain.ErrorCode;

@Getter
public class CustomException extends RuntimeException {

  private final ErrorCode errorCode;

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

}
