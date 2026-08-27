package com.example.bbs.controller;

import com.example.bbs.form.MemberForm;
import com.example.bbs.service.DuplicateMemberException;
import com.example.bbs.service.MemberService;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "members/create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("memberForm") MemberForm memberForm,
                          BindingResult bindingResult) {
        if (!Objects.equals(memberForm.getPassword(), memberForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "passwordMismatch", "비밀번호가 일치하지 않습니다.");
        }
        if (bindingResult.hasErrors()) {
            return "members/create";
        }

        try {
            memberService.join(memberForm.getUsername(), memberForm.getPassword(),
                    memberForm.getName(), memberForm.getEmail());
        } catch (DuplicateMemberException exception) {
            if (exception.getMessage().contains("아이디")) {
                bindingResult.rejectValue("username", "duplicate", exception.getMessage());
            } else {
                bindingResult.rejectValue("email", "duplicate", exception.getMessage());
            }
            return "members/create";
        }
        return "redirect:/login";
    }
}
