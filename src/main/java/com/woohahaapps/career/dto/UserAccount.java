package com.woohahaapps.career.dto;

import com.woohahaapps.career.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class UserAccount implements UserDetails {

    private final String username;
    private final String userRealName;
    private final String password;
    private final boolean accountNonLocked = true;// 계정 잠금 여부
    private final boolean accountNonExpired = true;// 사용자 계정 만료
    private final boolean credentialsNonExpired = true;// 비밀번호 만료
    private final boolean enabled = true;// 사용자 활성화 여부
    private Collection<? extends GrantedAuthority> authorities;// 사용자 권한 목록

    private final String email;

    public UserAccount(Member member) {
        this.username = member.getId();// 사용자ID(id)를 key field 인 username 으로 설정
        this.userRealName = member.getName();// 사용자 이름을 userRealName 으로 설정
        this.password = member.getPassword();
        this.email = member.getEmail();
        Collection<GrantedAuthority> roles =
                Arrays.stream(member.getRole().split(","))
                        .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList());
        this.authorities = roles;
    }

}
