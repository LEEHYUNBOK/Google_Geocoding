package com.example.springintroduction.controller;

import com.example.springintroduction.member.Member;
import com.example.springintroduction.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/signin")
    public String signInpage(Model model){
        Member member = new Member();
        model.addAttribute("member",member);
        return "signin";
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@ModelAttribute Member member, HttpServletRequest request) throws URISyntaxException {
        // 비밀번호 검사
        memberService.validate(member);

        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute("sessionUsername", member.getUsername());

        // 이것과 같은 것 => return "redirect:/";
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI("/")).build();
    }

    @GetMapping("/signup")
    public String signUpPage(Model model){

        // 같은 코드 = model.addAttribute("member", new Member());
        model.addAttribute(new Member()); // 간략하게 추가됨 member
        return "signup";
    }

    // Valid 어노테이션 class로 들어가서 검증 가능
    @PostMapping("/signup")
    public String signUp(@ModelAttribute @Valid Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup";
        }
        memberService.save(member);
        return "redirect:/#signup=ok";
    }

    @PostMapping("/signout")
    public String signOut(HttpServletRequest request){
        // 세션에 있는 정보 삭제
        request.getSession().invalidate();
        return "redirect:/";
    }
}
