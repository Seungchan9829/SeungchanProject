package ksc.ts.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userEmail;

    private String password;

    private String userName;
}
