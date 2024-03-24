package com.woohahaapps.career.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class SignUpForm {
    private String userid;
    private String username;
    private String email;
    private String password;
    private String password_confirm;
}
