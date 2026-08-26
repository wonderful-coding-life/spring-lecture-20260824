package com.example.restful.repository;

import com.example.restful.entity.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        List<Member> members = List.of(
                Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                Member.builder().name("윤광철").email("Kwangcheol@hanbit.co.kr").age(43).build()
        );
        memberRepository.saveAll(members);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @Disabled("이번 버전에서는 회원수 테스트 하는 것 스킵")
    public void testCount() {
        long count = memberRepository.count();
        List<Member> members = memberRepository.findAll();
        assertThat(count).isEqualTo(2);
        assertThat(members.size()).isEqualTo(2);
    }

    @RepeatedTest(5)
    @DisplayName("이메일 조회 테스트")
    public void testFindByEmail() {
        Member member = memberRepository.findByEmail("SeojunYoon@hanbit.co.kr").orElse(null);
        assertThat(member).isNotNull();
    }
}
