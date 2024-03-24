package com.woohahaapps.career.service;

import com.woohahaapps.career.domain.Member;
import com.woohahaapps.career.dto.PageInformation;
import com.woohahaapps.career.dto.PagerDto;
import com.woohahaapps.career.exception.CouldNotProcessException;
import com.woohahaapps.career.exception.DuplicateKeyItemException;
import com.woohahaapps.career.exception.ItemNotFoundException;
import com.woohahaapps.career.exception.NotAuthorizedException;
import com.woohahaapps.career.mapper.MemberMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public Member add(@NonNull Member member) throws NotAuthorizedException, CouldNotProcessException, ItemNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotAuthorizedException("인증되지 않았습니다.");
        }

        String userid = authentication.getName();
        if (userid.equals("anonymousUser"))
            userid = "guest";
        member.setCreator(userid);

        try {
            Integer affectedCount = memberMapper.createMember(member);
            if (affectedCount < 1) {
                log.error("Member.createMember failed");
                throw new CouldNotProcessException("회원을 저장할 수 없습니다.");
            }

            Optional<Member> result = memberMapper.selectMember(member.getId());// create 가져오기 위해서
            if (result.isEmpty()) {
                log.error(String.format("Member. 존재하지 않음 (고유번호: [%s])", member.getId()));
                throw new ItemNotFoundException("존재하지 않는 사용자입니다.");
            }
            return result.get();
        } catch (DuplicateKeyException e) {
            log.error(String.format("Member. 중복아이디 : [%s]\n%s", member.getId(), e.getMessage()));
            throw new DuplicateKeyItemException("중복된 아이디가 이미 존재합니다.");
        }
    }

    public Member get(String id) throws ItemNotFoundException {
        Optional<Member> result = memberMapper.selectMember(id);
        if (result.isEmpty()) {
            log.error(String.format("Member. 존재하지 않음 (고유번호: [%s])", id));
            throw new ItemNotFoundException("존재하지 않는 사용자입니다.");
        }
        return result.get();// 해당 고유번호의 아이템이 존재하지 않는 경우에 Exception 이 발생하므로 정확하게 구해진 값을 리턴하는게 맞다고 판단함.
    }

    public Member update(@NonNull Member member) throws ItemNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotAuthorizedException("인증되지 않았습니다.");
        }

        String userid = authentication.getName();
        if (userid.equals("anonymousUser")) {
            throw new NotAuthorizedException("인증되지 않았습니다.");
        }
        member.setModifier(userid);

        Integer affectedCount = memberMapper.updateMember(member);
        Optional<Member> result = memberMapper.selectMember(member.getId());// modify 가져오기 위해서
        if (result.isEmpty()) {
            log.error(String.format("Member. 존재하지 않음 (고유번호: [%s])", member.getId()));
            throw new ItemNotFoundException("존재하지 않는 사용자입니다.");
        }
        return result.get();// 해당 고유번호의 아이템이 존재하지 않는 경우에 Exception 이 발생하므로 정확하게 구해진 값을 리턴하는게 맞다고 판단함.
    }

    public Boolean delete(String id) {
        return (1 == memberMapper.deleteMember(id));
    }

    public PagerDto<List<Member>> getAll(Integer count_per_page, Integer current_page) {
        Long total_count = memberMapper.selectCount();
        Integer max_page = (int) Math.ceil((double) total_count / (double) count_per_page);

        if (max_page < 1)
            max_page = 1;
        if (max_page < current_page)
            current_page = max_page;

        List<Member> members = memberMapper.selectAllMember(count_per_page, current_page);

        PageInformation pageInformation = PageInformation.builder()
                .count_per_page(count_per_page)
                .current_page(current_page)
                .total_count(total_count)
                .max_page(max_page)
                .build();

        if (pageInformation.getMax_page() < 1) {
            pageInformation.setMax_page(1);
        }

        PagerDto pagerDto = PagerDto.builder()
                .page(pageInformation)
                .dto(members)
                .build();

        return pagerDto;
    }

}
