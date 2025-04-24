package ksc.ts.controller;

import jakarta.validation.Valid;
import ksc.ts.dto.order.OrderHistoryResponse;
import ksc.ts.dto.order.OrderRequest;
import ksc.ts.dto.order.OrderResponse;
import ksc.ts.model.User;
import ksc.ts.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> submitOrder(@AuthenticationPrincipal User user, @Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.submitOrder(user, orderRequest);

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderHistoryResponse>> getOrderHistoryBySymbol(@AuthenticationPrincipal User user, @RequestParam String symbol) {
        List<OrderHistoryResponse> orderHistoryResponse = orderService.getOrderHistoryBySymbol(user, symbol);

        return ResponseEntity.ok(orderHistoryResponse);


    }

}
