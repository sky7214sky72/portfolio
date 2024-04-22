package org.example.portfolio.sign.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.portfolio.global.domain.BaseTimeEntity;
import org.example.portfolio.global.domain.MemberType;
import org.example.portfolio.sign.adapter.in.dto.request.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Entity
@Table(name = "user", schema = "english_study_web")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String type;
  private String name;
  private String mail;
  private String password;
  private String provider;

  public static User from(SignUpRequest signUpRequest, PasswordEncoder passwordEncoder) {
    return User.builder()
        .type(MemberType.USER.name())
        .mail(signUpRequest.mail())
        .name(signUpRequest.name())
        .password(passwordEncoder.encode(signUpRequest.password()))
        .build();
  }

  @JsonIgnore
  public void updatePassword(PasswordEncoder passwordEncoder, String password) {
    this.password = passwordEncoder.encode(password);
  }
}
