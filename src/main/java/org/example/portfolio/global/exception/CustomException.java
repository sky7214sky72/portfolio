package org.example.portfolio.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.portfolio.global.domain.ErrorCode;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
  }
}
