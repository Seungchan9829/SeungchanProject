package ksc.ts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


// Jackson이 Json 변환을 수행할 때 getter 메서드를 찾는다
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private String userEmail;

//    public UserSignUpResponse(User user) {
//        this.id = user.getId();
//        this.userName = user.getUserName();
//        this.userEmail = user.getUserEmail();
//    }
}
