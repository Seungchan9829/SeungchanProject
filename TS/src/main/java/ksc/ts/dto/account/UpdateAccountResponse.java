package ksc.ts.dto.account;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class UpdateAccountResponse {

    Long id;

    private String accountNumber;

    private String accountPassword;
}
