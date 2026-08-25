package com.example.mybatis.mapper;

import com.example.mybatis.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insertMember(@Param("member") Member member);
    int updateMember(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age);
    int deleteMember(@Param("id") Long id);
    List<Member> selectAll();
    List<Member> selectAllOrderBy(@Param("order") String order, @Param("dir") String dir);
    Member selectById(@Param("id") Long id);
}
