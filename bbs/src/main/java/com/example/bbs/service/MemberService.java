package com.example.bbs.service;

import com.example.bbs.entity.Member;
import com.example.bbs.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(String username, String password, String name, String email) {
        if (memberRepository.existsByUsername(username)) {
            throw new DuplicateMemberException("이미 사용 중인 로그인 아이디입니다.");
        }
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateMemberException("이미 사용 중인 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        return memberRepository.save(Member.create(username, encodedPassword, name, email)).getId();
    }

    @Transactional(readOnly = true)
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));
    }
}
