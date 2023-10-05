package com.example.spring.service;

import com.example.spring.domain.Member;
import com.example.spring.repository.MemberRepository;
import com.example.spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //회원가입

    public Long join(Member member) {
        //같은 이름을 가진 중복 회원X
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");

                });
    }

    //전체회원 조회

    public List<Member> findMember(){
        return memberRepository.findAll();

    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);

    }
}
