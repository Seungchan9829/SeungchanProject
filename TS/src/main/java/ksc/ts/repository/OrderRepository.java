package ksc.ts.repository;

import jakarta.transaction.Transactional;
import ksc.ts.dto.order.OrderHistoryResponse;
import ksc.ts.model.Orders;
import ksc.ts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.filledQuantity = :filledQuantity WHERE o.orderId = :orderId")
    void updateOrders(@Param("orderId") Long orderId, @Param("filledQuantity")BigDecimal filledQuantity);

    @Query("""
        SELECT new ksc.ts.dto.order.OrderHistoryResponse(
            o.orderId,
            o.symbol,
            o.side,
            o.orderType,
            o.price,
            o.quantity,
            o.filledQuantity,
            o.status,
            o.createdAt)
        FROM Orders o
        WHERE o.user = :user AND o.symbol = :symbol
        ORDER BY o.createdAt DESC
        """)
    List<OrderHistoryResponse> getOrderHistoryBySymbol(@Param("user")User user, @Param("symbol") String symbol);




}
