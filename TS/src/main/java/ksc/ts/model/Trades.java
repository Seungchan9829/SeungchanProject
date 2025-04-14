package ksc.ts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trades {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeId;

    @ManyToOne
    @JoinColumn(name = "buy_order_id")
    private Orders buyOrder;

    @ManyToOne
    @JoinColumn(name = "sell_order_id")
    private Orders sellOrder;

    @Column
    private String symbol;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal quantity;

    @CreationTimestamp
    private LocalDateTime tradeTime;

    @ManyToOne
    @JoinColumn(name = "buy_user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "sell_user_id")
    private User seller;

}
