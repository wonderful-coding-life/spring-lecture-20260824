package com.example.mvc.controller;

import com.example.mvc.entity.Member;
import com.example.mvc.form.MemberForm;
import com.example.mvc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/member/add")
    public String getMemberAdd() {
        return "member-add";
    }

    @PostMapping("/member/add")
    public String postMemberAdd(Member member) {
        memberRepository.save(member);
        return "redirect:/member/list";
    }

    @GetMapping("/member/list")
    public String getMemberList(Model model) {
        var members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "member-list";
    }

    @GetMapping("/member/edit")
    public String getMemberEdit(@RequestParam("id") Long id, Model model) {
        var member = memberRepository.findById(id).orElseThrow();
        model.addAttribute("member", member);
        return "member-edit";
    }

    @PostMapping("/member/edit")
    public String getMemberEdit(Member member) {
        memberRepository.save(member);
        return "redirect:/member/list";
    }

    @GetMapping("/member/delete")
    public String deleteMember(@RequestParam("id") Long id) {
        memberRepository.deleteById(id);
        return "redirect:/member/list";
    }
}
