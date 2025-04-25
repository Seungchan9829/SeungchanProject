package ksc.ts.dto.order;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class OrderResponse {
    private Long orderId;

    private Long userId;

    private String symbol;

    private String side;

    private String orderType;

    private BigDecimal price;

    private BigDecimal quantity;

    private BigDecimal filledQuantity;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
