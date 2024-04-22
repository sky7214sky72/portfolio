package org.example.portfolio.global.domain;

import lombok.Getter;
import org.example.portfolio.global.exception.CustomException;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

  //400 잘못된 요청
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST.value(), "파라미터 값을 확인해주세요."),
  MAIL_PASSWORD_INVALID(HttpStatus.BAD_REQUEST.value(), "아이디 비밀번호가 일치하지 않습니다."),
  KAKAO_USER(HttpStatus.BAD_REQUEST.value(), "카카오로 가입된 계정입니다. 카카오로 로그인하기로 로그인해 주세요."),
  NAVER_USER(HttpStatus.BAD_REQUEST.value(), "네이버로 가입된 계정입니다. 네이버로 로그인하기로 로그인해 주세요."),
  GOOGLE_USER(HttpStatus.BAD_REQUEST.value(), "구글로 가입된 계정입니다. 구글로 로그인하기로 로그인해 주세요."),
  //404 NOT FOUND
  WORD_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "단어를 찾을 수 없습니다."),
  MAIL_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "가입된 이메일이 아닙니다."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "유저를 찾을수 없습니다."),
  //409 CONFLICT
  ALREADY_USER_SAVE(HttpStatus.CONFLICT.value(), "이미 가입한 유저입니다."),
  ALREADY_WORD_SAVE(HttpStatus.CONFLICT.value(), "이미 저장된 단어입니다."),
  //500 Server Error
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 에러입니다. 서버 팀에 연락주세요!"),
  OAUTH_SERVER_TYPE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "지원하지 않는 소셜 로그인 타입입니다.");

  @Getter
  private final int status;
  private final String message;

  ErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public CustomException toException() {
    return new CustomException(this);
  }
}
