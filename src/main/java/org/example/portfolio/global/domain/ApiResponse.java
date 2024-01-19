package org.example.portfolio.global.domain;

import org.springframework.http.HttpStatus;

public record ApiResponse(HttpStatus status, String message) {

  public static ApiResponse success(Object data) {
    return new ApiResponse(SuccessCode.SUCCESS.getStatus(), SuccessCode.SUCCESS.getMessage());
  }

  public static ApiResponse error(HttpStatus status, String message) {
    return new ApiResponse(status, message);
  }

}
