package org.example.portfolio.user.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpRequest(@Schema(description = "회원 비밀번호", example = "1234")
                            String password,
                            @Schema(description = "회원 이름", example = "최원석")
                            String name,
                            @Schema(description = "회원 메일", example = "cws070993@gmail.com")
                            String mail) {

}
