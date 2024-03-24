package com.woohahaapps.career.service;

import com.woohahaapps.career.domain.Member;
import com.woohahaapps.career.dto.UserAccount;
import com.woohahaapps.career.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private final MemberService memberService;

    @Autowired
    public LoginService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = null;
        try {
            member = memberService.get(username);
        } catch (ItemNotFoundException e) {
            //throw new UsernameNotFoundException("아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.");
            throw new BadCredentialsException("아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.");
        }
        return new UserAccount(member);
    }
}
