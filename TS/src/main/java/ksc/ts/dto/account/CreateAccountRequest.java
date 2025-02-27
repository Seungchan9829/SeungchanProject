package ksc.ts.dto.account;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAccountRequest {

    private Long userId;

    private String accountNumber;

    private String accountPassword;

}
