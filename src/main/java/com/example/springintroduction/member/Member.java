package com.example.springintroduction.member;
// lombok : getter setter 같은 가독성 안좋은 코드에 좋음
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

// lombok ex)
// Setter를 자동적으로 생성
@Setter
// Getter를 자동적으로 생성
@Getter
// 파라미터 없는 생성자 생성
@NoArgsConstructor
// 모든 필드 값을 파라미터로 받는 생성자 생성
@AllArgsConstructor
public class Member {

    // 조건문 선언
    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private String username;

    @Length(min = 2, max = 8, message = "2~8자 사이로 하세요")
    private String password;

}
