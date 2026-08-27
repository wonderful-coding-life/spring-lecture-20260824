package com.example.mvc.controller;

import com.example.mvc.entity.Member;
import com.example.mvc.form.MemberForm;
import com.example.mvc.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;

    @GetMapping("/signup")
    public String getSignup(@ModelAttribute("memberForm") MemberForm memberForm) {
        memberForm.setName("홍길동");
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@Valid @ModelAttribute("memberForm") MemberForm memberForm, BindingResult bindingResult) {

        if (memberRepository.findByEmail(memberForm.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "already.exist", "사용중인 이메일입니다.");
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        Member member = Member.builder()
                .name(memberForm.getName())
                .email(memberForm.getEmail())
                .age(memberForm.getAge())
                .password(memberForm.getPassword()).build();
        memberRepository.save(member);
        return "redirect:/member/list";
    }
}
