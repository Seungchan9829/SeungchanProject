package ksc.ts.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
public class GetAccountResponse {

    private Long id;

    private Long userId;

    private String accountNumber;

    private BigDecimal accountBalance;

}
