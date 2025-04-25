package ksc.ts.service;

import jakarta.transaction.Transactional;
import ksc.ts.dto.order.OrderHistoryResponse;
import ksc.ts.dto.order.OrderRequest;
import ksc.ts.dto.order.OrderResponse;
import ksc.ts.exception.OrderNotFoundException;
import ksc.ts.mapper.OrderMapper;
import ksc.ts.model.Orders;
import ksc.ts.model.User;
import ksc.ts.repository.OrderRepository;
import ksc.ts.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderBook orderBook; // 각 종목별 OrderBook을 관리할 수도 있지만, 예시로 하나의 OrderBook 사용
    private final OrderMapper orderMapper;

    public OrderResponse submitOrder(User user, OrderRequest orderRequest) {
        // DB에 Order 객체를 저장해야한다.
        Orders newOrder = Orders.builder()
                .user(user)
                .symbol(orderRequest.getSymbol())
                .side(orderRequest.getSide())
                .orderType(orderRequest.getOrderType())
                .price(orderRequest.getPrice())
                .quantity(orderRequest.getQuantity())
                .filledQuantity(BigDecimal.ZERO)
                .status("OPEN")
                .build();

        Orders savedOrder = orderRepository.save(newOrder);

        // 주문 추가 및 매칭
        orderBook.addOrder(savedOrder);
        orderBook.matchOrder(savedOrder);

        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .userId(savedOrder.getUser().getId())
                .symbol(savedOrder.getSymbol())
                .side(savedOrder.getSide())
                .orderType(savedOrder.getOrderType())
                .price(savedOrder.getPrice())
                .quantity(savedOrder.getQuantity())
                .filledQuantity(savedOrder.getFilledQuantity())
                .status(savedOrder.getStatus())
                .createdAt(savedOrder.getCreatedAt())
                .updatedAt(savedOrder.getUpdatedAt())
                .build();
    }

    public List<OrderHistoryResponse> getOrderHistoryBySymbol(User user, String symbol) {

        return orderRepository.getOrderHistoryBySymbol(user, symbol);
    }

    @Transactional
    public OrderResponse cancelOrder(User user, Long orderId) {

        Orders cancelTargetOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("주문을 찾을 수 없습니다."));

        if(!cancelTargetOrder.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("권한이 없습니다.");
        }
        // 오더북에서 해당 주문 제거
        orderBook.cancelOrder(cancelTargetOrder);

        // 상태 변경
        cancelTargetOrder.setStatus("Cancel");

        return orderMapper.orderToOrderResponse(cancelTargetOrder);
    }


}
