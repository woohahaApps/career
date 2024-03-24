package com.woohahaapps.career.mapper;

import com.woohahaapps.career.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    // C:Create
    Integer createMember(Member member) throws DuplicateKeyException;
    // U:Update
    Integer updateMember(Member member);
    // R:Read
    Optional<Member> selectMember(String id);
    // D:Delete
    Integer deleteMember(String id);
    // R:Read multiple
    List<Member> selectAllMember(Integer count_per_page, Integer current_page);
    Long selectCount();
}
