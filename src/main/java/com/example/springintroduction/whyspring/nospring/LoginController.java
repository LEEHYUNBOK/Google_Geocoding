package com.example.springintroduction.whyspring.nospring;

/**
 *  로그인 작업을 수행한다.
 *  Login 클래스는 SocialLogin, TokenManager, User 를 사용한다.
 *      User 는 UserRepository 를 사용한다.
 */

public class LoginController {

    private final Login login;

    // 생성자
    public LoginController(Login login) {
        this.login = login;
    }

    public static void main(String[] args) {
        // 스프링을 쓰지 않을 경우 new 생성자를 안에 만들고 또 안에 만드는 작업을 반복해야될 경우도 생긴다.
        LoginController loginController = new LoginController(new Login(new NaverLogin(), new TokenManager(),
                new User(new UserRepository())));

        loginController.login.doLogin();
    }
}
