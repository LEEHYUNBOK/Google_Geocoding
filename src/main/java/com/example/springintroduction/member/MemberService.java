package com.example.springintroduction.member;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 요구되는 매개변수들의 구조체
@RequiredArgsConstructor
public class MemberService {
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    // Member 객체 저장.
    // 비즈니스 로직은 Service 클래스가 수행, 저장은 Service 클래스가 Repository 클래스에게 맡긴다.
    public void save(Member member){
        
        // passwordEncoder를 통해 암호화 후 다시 저장
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        memberRepository.save(member);
    }

    // 전체 조회, repository class에 기능 단순 위임
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    // 이름으로 단건 조회, repository class에 기능 단순 위임
    public List<Member> findByUsername(String username){
        return memberRepository.findByUsername(username);
    }

    // 로그인 시도한 회원정보 검증
    public void validate(Member member) {
        Member foundMember = MemberRepository.memberMap.get(member.getUsername());

        if ( !passwordEncoder.matches(member.getPassword(), foundMember.getPassword())){
            throw new IllegalArgumentException("비밀번호 틀림");
        }
    }


}
