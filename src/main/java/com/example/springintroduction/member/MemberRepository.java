package com.example.springintroduction.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {


    // static 에 대한 내용 https://tecoble.techcourse.co.kr/post/2020-11-07-singleton/ 한번 읽어볼 것
    // DB
    public static Map<String, Member> memberMap = new HashMap<>();


    // member 저장
    public void save(Member member){
        memberMap.put(member.getUsername(), member);
    }

    // 멤버 모두 조회
    public List<Member> findAll(){
        Collection<Member> values = memberMap.values();
        return new ArrayList<>(values);
    }

    // 이름으로 찾기
    public List<Member> findByUsername(String username){
        return List.of(memberMap.get(username));
    }
}
