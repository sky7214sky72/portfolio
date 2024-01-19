package org.example.portfolio.global.exception;

import static org.example.portfolio.global.domain.ErrorCode.INTERNAL_SERVER_ERROR;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.security.SignatureException;
import java.util.NoSuchElementException;
import org.example.portfolio.global.domain.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({CustomException.class})
  protected ResponseEntity<ApiResponse> handleCustomException(CustomException e) {
    return ResponseEntity.status(e.getErrorCode().getStatus())
        .body(ApiResponse.error(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()));
  }

  @ExceptionHandler({Exception.class})
  protected ResponseEntity<ApiResponse> handleServerException(Exception e) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR.getStatus())
        .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR,
            INTERNAL_SERVER_ERROR.getMessage()));
  }

  @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
  public ResponseEntity<ApiResponse> handleCommonException(Exception e) {
    return ResponseEntity.badRequest()
        .body(ApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponse> handleAccessDeniedException() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(ApiResponse.error(HttpStatus.FORBIDDEN, "접근이 거부되었습니다."));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> handleUnexpectedException() {
    return ResponseEntity.internalServerError()
        .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다."));
  }

  @ExceptionHandler(SignatureException.class)
  public ResponseEntity<ApiResponse> handleSignatureException() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."));
  }

  @ExceptionHandler(MalformedJwtException.class)
  public ResponseEntity<ApiResponse> handleMalformedJwtException() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다."));
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<ApiResponse> handleExpiredJwtException() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.error(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다. 다시 로그인해주세요."));
  }
}
