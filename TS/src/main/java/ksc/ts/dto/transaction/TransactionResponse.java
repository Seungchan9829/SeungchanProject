package ksc.ts.dto.transaction;

import ksc.ts.model.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class TransactionResponse {
    private Long id;

    private Type type;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private BigDecimal updatedBalance;

}
