package com.example.restful.service;

import com.example.restful.dto.MemberRequest;
import com.example.restful.dto.MemberResponse;
import com.example.restful.entity.Member;
import com.example.restful.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse subscribe(MemberRequest memberRequest) {
        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .password(memberRequest.getEmail())
                .enabled(true).build();
        memberRepository.save(member);

        return mapToMemberResponse(member);
    }

//    public List<MemberResponse> subscribeBatch(List<MemberRequest> memberRequests) {
//        List<MemberResponse> memberResponses =  new ArrayList<MemberResponse>();
//        for (MemberRequest memberRequest : memberRequests) {
//            MemberResponse memberResponse = subscribe(memberRequest);
//            memberResponses.add(memberResponse);
//        }
//        return memberResponses;
//    }

    @Transactional
    public List<MemberResponse> subscribeBatch(List<MemberRequest> memberRequests) {
        return memberRequests.stream().map(this::subscribe).toList();
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return mapToMemberResponse(member);
    }

    private MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }
}
