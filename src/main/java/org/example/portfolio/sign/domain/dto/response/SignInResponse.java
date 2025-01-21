package org.example.portfolio.sign.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInResponse(
    @Schema(description = "회원 이름", example = "콜라곰")
    String name,
    @Schema(description = "메일 주소", example = "cws070993@gmail.com")
    String mail,
    String token) {

}
