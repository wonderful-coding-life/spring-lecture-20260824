package com.example.mybatis;

import com.example.mybatis.mapper.MemberMapper;
import com.example.mybatis.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Slf4j
public class TestXmlApplication implements ApplicationRunner {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Member member = Member.builder()
//                .name("윤광철")
//                .email("KwangcheolYoon@campus.co.kr")
//                .age(43).build();
//        int count = memberMapper.insertMember(member);
//        log.info("count = {}", count);
//        log.info("회원 {}", member);

//        int count = memberMapper.updateMember(5L, "윤동희", 11);
//        Member member = memberMapper.selectById(5L);
//        log.info("count = {}", count);
//        log.info("회원 {}", member);

        //List<Member> members = memberMapper.selectAll();
        String order = "age";
        String dir = "asc";
        List<Member> members = memberMapper.selectAllOrderBy(order, dir);
        for (Member member : members) {
            log.info("회원 {}", member);
        }
    }
}
