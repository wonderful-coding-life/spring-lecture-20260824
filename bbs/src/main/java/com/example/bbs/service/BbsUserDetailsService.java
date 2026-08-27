package com.example.bbs.service;

import com.example.bbs.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BbsUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member;
        try {
            member = memberService.findByUsername(username);
        } catch (MemberNotFoundException exception) {
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다.", exception);
        }
        return new BbsUserDetails(member.getUsername(), member.getPassword(), member.getName(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
