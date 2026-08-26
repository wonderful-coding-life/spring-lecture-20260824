package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/model")
    public String getModel(Model model) {
        model.addAttribute("name", "윤서준");
        model.addAttribute("email", "SeojunYoon@campus.co.kr");

        Member member = Member.builder()
                .name("김희선")
                .email("HeesunKim@campus.co.kr")
                .age(18).build();
        model.addAttribute("me", member);

        return "model";
    }

    @GetMapping("/list")
    public String getList(Model model) {

        var members = List.of(
                Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").age(23).build(),
                Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build()
        );
        model.addAttribute("members", members);

        return "list";
    }

    @GetMapping("/utility")
    public String getUtility(Model model) {
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now);
        return "utility";
    }

}
