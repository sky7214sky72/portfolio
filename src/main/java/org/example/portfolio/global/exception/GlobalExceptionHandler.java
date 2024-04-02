package org.example.portfolio.global.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.security.SignatureException;
import java.util.NoSuchElementException;
import org.example.portfolio.global.domain.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler({CustomException.class})
  protected ResponseEntity<ApiResponse> handleCustomException(CustomException e) {
    logger.error("CustomException occurred: {}", e.getMessage(), e);
    return ResponseEntity.status(e.getErrorCode().getStatus())
        .body(ApiResponse.error(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()));
  }

  @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
  public ResponseEntity<ApiResponse> handleCommonException(Exception e) {
    logger.error("Common exception occurred: {}", e.getMessage(), e);
    return ResponseEntity.badRequest()
        .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponse> handleAccessDeniedException() {
    logger.error("접근이 거부되었습니다.");
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(ApiResponse.error(HttpStatus.FORBIDDEN.value(), "접근이 거부되었습니다."));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ApiResponse> handleUnexpectedException(Exception ex) {
    logger.error("Internal server error occurred: {}", ex.getMessage(), ex);
    return ResponseEntity.internalServerError()
        .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버에 문제가 발생했습니다."));
  }

  @ExceptionHandler(SignatureException.class)
  public ResponseEntity<ApiResponse> handleSignatureException() {
    logger.error("토큰이 유효하지 않습니다.");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "토큰이 유효하지 않습니다."));
  }

  @ExceptionHandler(MalformedJwtException.class)
  public ResponseEntity<ApiResponse> handleMalformedJwtException() {
    logger.error("올바르지 않은 토큰입니다.");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "올바르지 않은 토큰입니다."));
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<ApiResponse> handleExpiredJwtException() {
    logger.error("토큰이 만료되었습니다. 다시 로그인해주세요.");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error(HttpStatus.UNAUTHORIZED.value(), "토큰이 만료되었습니다. 다시 로그인해주세요."));
  }
}
