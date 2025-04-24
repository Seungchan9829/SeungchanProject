package ksc.ts.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderHistoryResponse {
    private Long orderId;

    private String symbol;

    private String side;

    private String orderType;

    private BigDecimal price;

    private BigDecimal quantity;

    private BigDecimal filledQuantity;

    private String status;

    private LocalDateTime createdAt;
}
