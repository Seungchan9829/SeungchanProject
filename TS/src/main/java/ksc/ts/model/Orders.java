package ksc.ts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String symbol;

    @Column
    private String side;

    @Column
    private String orderType;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal quantity;

    @Column
    private BigDecimal filledQuantity;

    @Column
    private String status;

    @Column
    private String timeInForce;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

}
