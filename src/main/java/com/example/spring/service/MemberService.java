package com.example.spring.service;

import com.example.spring.domain.Member;
import com.example.spring.repository.MemberRepository;
import com.example.spring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //회원가입

    public Long join(Member member) {
        //같은 이름을 가진 중복 회원X
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        memberRepository.save(member);
        return member.getId();

    }
}
