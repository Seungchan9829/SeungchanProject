package ksc.ts.service;


import ksc.ts.model.Orders;
import ksc.ts.model.Trades;
import ksc.ts.repository.OrderRepository;
import ksc.ts.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Component
public class OrderBook {

    private final TreeMap<BigDecimal, Queue<Orders>> bidBook = new TreeMap<>(Collections.reverseOrder());

    private final TreeMap<BigDecimal, Queue<Orders>> askBook = new TreeMap<>();

    private final Map<Long, Orders> orderMap = new HashMap<>();

    private final TradeRepository tradeRepository;

    private final OrderRepository orderRepository;

    public void addOrder(Orders orders){
        // 어떤 종류의 주문인지 확인
        String orderSide = orders.getSide();
        // 주문 가격 확인.`
        BigDecimal orderPrice;
        // 주문 타입 확인
        String orderType = orders.getOrderType();

        // 주문 가격 확인
        if (orderType.equals("지정가")) {
            orderPrice = orders.getPrice();
        } else {
            orderPrice = BigDecimal.valueOf(999999999);
        }

        // 주문 Id 확인
        Long orderId = orders.getOrderId();
        orderMap.put(orderId, orders);
        // 주문 넣기
        if(orderSide.equals("B")){
            Queue<Orders> ordersQueue = bidBook.computeIfAbsent(orderPrice, k -> new ArrayDeque<>());
            ordersQueue.add(orders);
        }
        else if(orderSide.equals("S")){
            Queue<Orders> ordersQueue = askBook.computeIfAbsent(orderPrice, k -> new ArrayDeque<>());
            ordersQueue.add(orders);
        }
        else {
            throw new IllegalArgumentException("올바르지 않은 주문 형식입니다. " + orderSide);
        }

    }

    public void cancelOrder(Orders orders){
        // 오더 주문 ID
        Long orderId = orders.getOrderId();
        BigDecimal orderPrice = orders.getPrice();

        Orders foundOrder = orderMap.remove(orderId);

        if(foundOrder == null){
            throw new IllegalArgumentException("취소할 주문이 존재 하지 않습니다");
        }

        if("B".equals(foundOrder.getOrderType())){
            Queue<Orders> ordersQueue = bidBook.get(orderPrice);
            if(ordersQueue != null){
                ordersQueue.remove(foundOrder);
                if(ordersQueue.isEmpty()){
                    bidBook.remove(orderPrice);
                }
            }
        } else if("S".equals(foundOrder.getOrderType())){
            Queue<Orders> ordersQueue = askBook.get(orderPrice);
            if(ordersQueue != null){
                ordersQueue.remove(foundOrder);
                if(ordersQueue.isEmpty()){
                    askBook.remove(orderPrice);
                }
            }
        }
    }

    public void matchOrder(Orders order){
        String orderSide = order.getSide();
        BigDecimal orderPrice = order.getPrice();

        // 매수 주문일 경우
        if(orderSide.equals("B")){
            while(!askBook.isEmpty() && orderPrice.compareTo(askBook.firstKey()) >= 0) {
                // 가장 낮은 매도 호가를 가지고온다
                Queue<Orders> askQueue = askBook.get(askBook.firstKey());

                // askQueue 비어있는 지 확인
                if(askQueue == null || askQueue.isEmpty()){
                    askBook.remove(askBook.firstKey());
                }
                Orders askOrder = askQueue.peek(); // 첫 번째 요소를 반환하지만, 해당 요소를 제거 하지는 않음.

                if(askOrder == null) {
                    askQueue.poll();
                    if(askQueue.isEmpty()){
                        askBook.remove(askBook.firstKey());
                    }
                }

                // 남아있는 주문 수량
                BigDecimal remainingOrder = order.getQuantity().subtract(order.getFilledQuantity());
                BigDecimal remainingAskOrder = askOrder.getQuantity().subtract(askOrder.getFilledQuantity());

                // 체결할 수량 결정
                BigDecimal tradeQuantity = remainingOrder.min(remainingAskOrder);

                // 체결 로직 실행. 수량 업데이트 및  거래기록 생성
                Trades newTrade = Trades.builder()
                        .buyOrder(order)
                        .sellOrder(askOrder)
                        .symbol("AAPL")
                        .price(orderPrice)
                        .quantity(tradeQuantity)
                        .buyer(order.getUser())
                        .seller(askOrder.getUser())
                        .build();

                tradeRepository.save(newTrade);

                // 두 개의 주문 수량 업데이트
                order.setFilledQuantity(order.getFilledQuantity().add(tradeQuantity));
                askOrder.setFilledQuantity(askOrder.getFilledQuantity().add(tradeQuantity));

                orderRepository.updateOrders(order.getOrderId(), order.getFilledQuantity());
                orderRepository.updateOrders(askOrder.getOrderId(), askOrder.getFilledQuantity());

                // 주문과 매칭되는 주문의 종료 조건
                if (askOrder.getFilledQuantity().compareTo(askOrder.getQuantity()) == 0) {
                    askQueue.poll();
                    orderMap.remove(askOrder.getOrderId());
                }
                // 주문이 모두 채워졌을 경우
                if(order.getFilledQuantity().compareTo(order.getQuantity()) == 0){
                    break;
                }

                if(askQueue.isEmpty()){
                    askBook.remove(askBook.firstKey());
                }



            }

        }
        // 매도 주문일 경우
        if(orderSide.equals("S")){
            while(!bidBook.isEmpty() && orderPrice.compareTo(bidBook.firstKey()) <= 0) {
                // 가장 높은 매수 호가를 가지고 온다
                Queue<Orders> bidQueue = bidBook.get(bidBook.firstKey());

                if(bidQueue == null || bidQueue.isEmpty()){
                    bidBook.remove(bidBook.firstKey());
                }
                Orders bidOrder = bidQueue.peek();

                if (bidOrder == null) {
                    bidQueue.poll();
                    if(bidQueue.isEmpty()){
                        bidBook.remove(bidBook.firstKey());
                    }
                }
                // 남아 있는 수량 결정
                BigDecimal remainingOrder = order.getQuantity().subtract(order.getFilledQuantity());
                BigDecimal remainingAskOrder = bidOrder.getQuantity().subtract(bidOrder.getFilledQuantity());

                // 체결할 수량 결정
                BigDecimal tradeQuantity = remainingOrder.min(remainingAskOrder);

                // 체결 로직 실행
                Trades newTrade = Trades.builder()
                        .buyOrder(bidOrder)
                        .sellOrder(order)
                        .symbol("AAPL")
                        .price(orderPrice)
                        .quantity(tradeQuantity)
                        .buyer(bidOrder.getUser())
                        .seller(order.getUser())
                        .build();

                tradeRepository.save(newTrade);

                // 두 개의 주문 수량 업데이트

                // 두 개의 주문 수량 업데이트
                order.setFilledQuantity(order.getFilledQuantity().add(tradeQuantity));
                bidOrder.setFilledQuantity(bidOrder.getFilledQuantity().add(tradeQuantity));

                orderRepository.updateOrders(order.getOrderId(), order.getFilledQuantity());
                orderRepository.updateOrders(bidOrder.getOrderId(), bidOrder.getFilledQuantity());

                if(bidOrder.getFilledQuantity().compareTo(bidOrder.getQuantity()) == 0){
                    bidQueue.poll();
                    orderMap.remove(bidOrder.getOrderId());
                }

                if(order.getFilledQuantity().compareTo(order.getQuantity()) == 0){
                    break;
                }

                if(bidQueue.isEmpty()){
                    bidBook.remove(bidBook.firstKey());
                }
            }
        }
    }
}
