import React, {useState} from 'react';
import { useSubmitOrder } from './hooks/useSubmitOrder';

export default function BuyOrderForm() {
    const [symbol, setSymbol] = useState("AAPL")
    const [price, setPrice] = useState(0)
    const [quantity, setQuantity] = useState(0) 
    const [type, setType] = useState("limit")
    const {mutate : submitOrderMutate, isLodaing, isSuccess, isError, error} = useSubmitOrder();
    
    const handleOrderSubmit = () => {
        submitOrderMutate({
            symbol : symbol,
            side : "B",
            type : type,
            quantity : quantity, 
            ...(type === "limit" && {price : price})
        })
    }

    return(
        <div class ="flex-col h-122 px-4 pt-4">
        <div class = "flex justify-between h-9 pb-4">
            <div class ="w-30">주문유형</div>
            <div class = "flex">
                <div class="flex items-center pr-5"> 
                    <input type="radio" name = "orderType" value = "limit" checked = {type === "limit"} onChange={(e) => setType(e.target.value)}></input>
                    <div class = "pl-2">지정가</div>
                </div>
                <div class="flex items-center"> 
                    <input type="radio" name="orderType" value="market" checked = {type === "market"} onChange= {(e) => setType(e.target.value)}></input>
                    <div class = "pl-2">시장가</div>
                </div>
            </div>
        </div>
        <div class = "flex-col h-10 mb-4">
            <div class = "flex justify-between">
                <div>주문가능</div>
                <div>0 주</div>
            </div>
        </div>
        <div class = "flex items-center justify-between h-10 mb-2">
            <div class ="w-32">매수가격 (KRW)</div>
            <div class="flex items-center border border-gray-500 h-9 rounded overflow-hidden">

            <input 
                type="text"
                class="flex-1 px-2 text-right focus:outline-none"
                value={price}
                onChange = {(e) => setPrice(e.target.value)}
            />
            <button class="px-3 h-full border-l border-gray-300 text-gray-700 hover:bg-gray-200" onClick={() => setPrice(prev => Math.max(0, prev-100))}>
                –
            </button>
            <button class="px-3 h-full border-l border-gray-300 text-gray-700 hover:bg-gray-200" onClick = {() => setPrice(Number(price) + 100)}>
                +
            </button>
            </div>
        </div>
        <div class = "flex-col h-21 mb-3">
            <div class ="flex items-center h-10 w-full justify-between mb-2">
                <div>주문수량</div>
                <div class = "flex items-center border-1 border-gray-300 w-80 h-full">
                    <input type = "text" value = {quantity} class ="text-right w-full px-3" onChange = {(e) => setQuantity(e.target.value)}/>
                </div>
            </div>
            <div class ="flex h-10 w-full justify-end">
                <div class = "flex">
                    <button class = "w-15 mr-2 border-1 border-gray-300">10%</button>
                    <button class = "w-15 mr-2 border-1 border-gray-300">25%</button>
                    <button class = "w-15 mr-2 border-1 border-gray-300">50%</button>
                    <button class = "w-15 border-1 border-gray-300">100%</button>
                </div>
            </div>

        </div>
        <div class ="flex items-center h-10 w-full justify-between mb-2">
                <div>주문총액(KRW)</div>
                <div class = "flex items-center border-1 border-gray-300 w-80 h-full">
                    <input type = "text" value = {price * quantity} class ="text-right w-full px-3" disabled/>
                </div>
        </div>
        <div class = "w-full h-24 p-2 mt-10">
            <div class ="flex w-full justify-between">
                <button class = "w-31 h-10 border-1 bg-gray-500 text-white">초기화</button>
                <button class = "w-75 h-10 border-1 bg-red-300 text-white">매수</button>
            </div>

        </div>
    </div>
    )
}