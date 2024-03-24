package com.woohahaapps.career.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Alias("Member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Member {
    @NotBlank(message="아이디는 빈값일 수 없습니다.")
    @Size(min=1, max=50, message="아이디는 1 ~ 50자입니다.")
    private String id;

    @NotBlank(message="이름은 빈값일 수 없습니다.")
    @Size(min=1, max=100, message="이름은 1 ~ 100자입니다.")
    private String name;

    @NotBlank(message="비밀번호는 빈값일 수 없습니다.")
    private String password;

    @NotBlank(message="비밀번호 확인은 빈값일 수 없습니다.")
    private String password_confirm;

    @NotBlank(message="이메일은 빈값일 수 없습니다.")
    @Size(min=1, max=100, message="이메일 1 ~ 100자입니다.")
    @Email
    private String email;

    @Builder.Default
    private Boolean validated = false;

    @Builder.Default
    private String role = "ROLE_USER";

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp create;
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp modify;
    private String modifier;
}
