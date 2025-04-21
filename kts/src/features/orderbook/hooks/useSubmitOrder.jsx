import { useMutation, useQueryClient } from "@tanstack/react-query"
import { submitOrder } from "../api/orderApi";

export const useSubmitOrder = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (orderData) => submitOrder(orderData),
        onSuccess: () => {
            // 성공했을 때 실행되는 콜백 함수
            // queryClient.invalidateQueries({ queryKey: ['orderHistory'] });

        },
        onError: (err) => {
            // 실패했을 때 실행되는 콜백 함수
            // console.error('❌ 주문 실패:', err);
        }
    })
}


