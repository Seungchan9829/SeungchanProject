package ksc.ts.dto.transaction;

import ksc.ts.model.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
public class TransactionRequest {

    private Type type;

    private BigDecimal amount;

}
