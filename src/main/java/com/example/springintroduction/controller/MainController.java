package com.example.springintroduction.controller;

import com.example.springintroduction.member.Member;
import com.example.springintroduction.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


// 메인화면의 자잘한 기능들을 수행하는 컨트롤러
@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    // 주소 /에 get 요청 => main 페이지 접속
    // 해당 path의 Get 요청 시 실행
    /**
     *  in Spring MVC, "request parameters" map to query parameters, form data, and parts in multipart requests
     *  쿼리 파라미터와 폼 데이터를 받는다.
     * */
    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(required = false) String query){

        if(query == null){
            List<Member> members = memberService.findAll();
            model.addAttribute("members",members);
            return "home";

        }

        List<Member> members = memberService.findByUsername(query);
        model.addAttribute("members",members);
        return "home";
    }

    // 더미 데이터 생성
    @PostConstruct
    public void createDummyData(){
        memberService.save(new Member("test","1234"));
        memberService.save(new Member("testing","1234"));
    }


    @GetMapping("formembers")
    public String forMembers(HttpServletRequest request){

        // 파라미터를 false로 하면, 기존 세션이 유효할 경우만 해당 세션울 가져옴
        HttpSession session = request.getSession(false);

        if( session == null || session.getAttribute("sessionUsername") == null){
            // 미인증 사용자 홈 화면을 강제 이동
            return "redirect:/";
        }
        return "formembers";
    }

}
