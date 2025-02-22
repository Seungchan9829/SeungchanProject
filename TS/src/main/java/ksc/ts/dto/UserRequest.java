package ksc.ts.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //
@NoArgsConstructor // 기본 생성자 자동 추가 @RequestBody 매핑 시 필수
@AllArgsConstructor // 모든 필드를 포함한 생성자 자동 추가
public class UserRequest {
    private String userName;

    private String userEmail;

    private String password;

}


