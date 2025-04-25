package ksc.ts.mapper;

import ksc.ts.dto.order.OrderResponse;
import ksc.ts.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderResponse orderToOrderResponse(Orders order);
}
