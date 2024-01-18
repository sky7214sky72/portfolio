package org.example.portfolio.user.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.Assert;

public record SignUpRequest(@Schema(description = "회원 비밀번호", example = "1234")
                            String password,
                            @Schema(description = "회원 이름", example = "최원석")
                            String name,
                            @Schema(description = "회원 메일", example = "cws070993@gmail.com")
                            String mail) {

  public SignUpRequest {
    Assert.hasText(mail, "메일은 필수 입니다.");
    Assert.hasText(password, "패스워드는 필수 입니다.");
    Assert.isTrue(password.length() > 6, "패스워드는 6자리 이상이어야 합니다.");
  }
}
