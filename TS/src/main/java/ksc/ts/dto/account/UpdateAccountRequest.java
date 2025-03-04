package ksc.ts.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class UpdateAccountRequest {

    Long id;

    private String accountNumber;

    private String accountPassword;



}
