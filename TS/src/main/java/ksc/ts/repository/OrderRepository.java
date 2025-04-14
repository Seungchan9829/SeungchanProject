package ksc.ts.repository;

import jakarta.transaction.Transactional;
import ksc.ts.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.filledQuantity = :filledQuantity WHERE o.orderId = :orderId")
    void updateOrders(@Param("orderId") Long orderId, @Param("filledQuantity")BigDecimal filledQuantity);
}
