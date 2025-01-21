package org.example.portfolio.sign.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignInRequest(@Schema(description = "회원 메일", example = "cws070993@gmail.com")
                            String mail,
                            @Schema(description = "회원 비밀번호", example = "1234")
                            String password) {

}
