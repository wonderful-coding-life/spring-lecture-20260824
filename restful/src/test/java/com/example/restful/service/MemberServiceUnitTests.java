package com.example.restful.service;

import com.example.restful.dto.MemberResponse;
import com.example.restful.entity.Member;
import com.example.restful.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberServiceUnitTests {
    @MockitoBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void testFindById() {
        when(memberRepository.findById(1L)).thenReturn(
                Optional.of(Member.builder()
                        .id(1L)
                        .name("윤서준")
                        .email("SeojunYoon@campus.co.kr")
                        .age(10).build())
        );

        MemberResponse memberResponse = memberService.findById(1L);
        assertThat(memberResponse.getName()).isEqualTo("윤서준");
    }
}
