package com.example.jpa;

import com.example.jpa.entity.Member;
import com.example.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        memberRepository.deleteById(5L);

        List<Member> members = memberRepository.findByOrderByAgeAscNameAsc();
        for (Member member : members) {
            log.info("회원 {}", member);
        }

//        Member member = Member.builder()
//                .name("김희선")
//                .email("HeesunKim@campus.co.kr")
//                .age(18).build();
//        memberRepository.save(member);
//
//        Member me = memberRepository.findById(member.getId()).orElseThrow();
//        log.info("내 정보 {}", me);
//
//        me.setAge(19);
//        memberRepository.save(me);
    }
}
