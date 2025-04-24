package ksc.ts.service;

import ksc.ts.dto.order.OrderRequest;
import ksc.ts.dto.order.OrderResponse;
import ksc.ts.model.Orders;
import ksc.ts.model.Role;
import ksc.ts.model.User;
import ksc.ts.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderBook orderBook;

    @InjectMocks
    OrderService orderService;

    private User user;
    private OrderRequest orderRequest;

    @BeforeEach
    void setUp() {
        // User 객체 설정
        user = new User();
        user.setId(1L);
        user.setUserName("김승찬");
        user.setPassword("123456");
        user.setRole(Role.USER);

        // 지정가 주문 요청 객체
        orderRequest = OrderRequest.builder()
                .symbol("AAPL")
                .side("B")
                .price(BigDecimal.valueOf(100000))
                .quantity(BigDecimal.valueOf(10))
                .build();

    }

    @Test
    void submitOrder() {
        when(orderRepository.save(any(Orders.class)))
                .thenAnswer(inv -> {
                    Orders orders = inv.getArgument(0);
                    orders.setOrderId(1L);
                    orders.setUser(user);
                    return orders;
                });


        OrderResponse orderResponse = orderService.submitOrder(user, orderRequest);

        assertEquals("OPEN", orderResponse.getStatus());
    }

}