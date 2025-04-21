import api from "../../../shared/api/axiosInstance"

export const submitOrder =  async (orderData) => {
    const orderRequest = {
        symbol : orderData.symbol,
        side : orderData.side,
        price : orderData.price,
        quantity : orderData.quantity    
    }

    const response = await api.post(`/api/orders`, orderRequest)

    return response.data
}