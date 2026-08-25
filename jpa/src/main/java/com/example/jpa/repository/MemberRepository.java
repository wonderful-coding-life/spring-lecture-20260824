package com.example.jpa.repository;

import com.example.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNameOrEmail(String name, String email);
    List<Member> findByNameAndAgeIsGreaterThanEqual(String name, Integer age);
    List<Member> findByOrderByAgeAscNameAsc();
}
