package org.example.portfolio.global.domain;

public record ApiResponse(int code, String message) {

  public static ApiResponse error(int status, String message) {
    return new ApiResponse(status, message);
  }

}
