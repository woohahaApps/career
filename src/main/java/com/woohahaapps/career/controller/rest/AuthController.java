package com.woohahaapps.career.controller.rest;

import com.woohahaapps.career.domain.Member;
import com.woohahaapps.career.dto.SignUpForm;
import com.woohahaapps.career.exception.ItemNotFoundException;
import com.woohahaapps.career.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("signup")
    public ResponseEntity<Member> signUp(SignUpForm signUpForm) throws ItemNotFoundException {
        System.out.println(signUpForm);
        Member member = Member.builder()
                .id(signUpForm.getUserid())
                .name(signUpForm.getUsername())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .email(signUpForm.getEmail())
                .build();

        Member add = memberService.add(member);
        System.out.println(add);
        return ResponseEntity.ok().body(add);
    }
}
