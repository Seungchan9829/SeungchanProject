package ksc.ts.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    private String symbol;

    @NotNull
    private String side;

    @NotNull
    @DecimalMin(value = "0", inclusive = true, message = "0 이상의 가격이어야 합니다.")
    private BigDecimal price;

    @NotNull
    @DecimalMin(value = "0", inclusive = true, message = "0 이상의 수량이어야 합니다.")
    private BigDecimal quantity;

    @NotNull
    private String orderType;
}
