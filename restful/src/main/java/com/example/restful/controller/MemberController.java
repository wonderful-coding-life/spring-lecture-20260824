package com.example.restful.controller;

import com.example.restful.dto.MemberRequest;
import com.example.restful.dto.MemberResponse;
import com.example.restful.entity.Member;
import com.example.restful.repository.MemberRepository;
import com.example.restful.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponse postMembers(@RequestBody MemberRequest memberRequest) {
        return memberService.subscribe(memberRequest);
    }

    @PostMapping("/api/v2/members")
    public List<MemberResponse> postMembersBatch(@RequestBody List<MemberRequest> memberRequests) {
        return memberService.subscribeBatch(memberRequests);
    }

    @GetMapping("/members")
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
