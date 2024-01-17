package org.example.portfolio.user;

import org.example.portfolio.user.adapter.in.dto.request.SignUpRequest;

public class UserSteps {

  public static SignUpRequest 회원가입정보_생성() {
    return new SignUpRequest("1234", "최원석", "cws070993@gmail.com");
  }
}
