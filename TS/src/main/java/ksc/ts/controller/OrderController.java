package ksc.ts.controller;

import jakarta.validation.Valid;
import ksc.ts.dto.order.OrderRequest;
import ksc.ts.dto.order.OrderResponse;
import ksc.ts.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> submitOrder(@AuthenticationPrincipal User user, @Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.submitOrder(user, orderRequest);

        return ResponseEntity.ok(orderResponse);
    }

}
