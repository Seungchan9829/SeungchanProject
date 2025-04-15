import React from 'react'


export default function OrderBook() {
    const sellOrder = [
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100}
    ]
    
    const buyOrder = [
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100}
    ]

    const tracnsactionHistory = [
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
        {id : 1, price : 10000, quantity : 100},
    ]
    return(
        <div class = "flex flex-col bg-white w-64 h-160 border">
            <div class ="flex items-center basis-1/16 px-4 border-b-2">
                <div>호가창</div>
            </div>
            <div class ="flex flex-col basis-10/16">
                <div class="flex basis-1/18 border-b-1">
                    <div class = "basis-1/2 pl-1 border-r-1" >가격</div>
                    <div class = "basis-1/2 pl-1" >수량</div>
                </div>
                <div class="basis-7/18">
                    {sellOrder.map((order, index) => (
                        <div class= "flex">
                            <div class = "basis-1/2">{order.price}</div>
                            <div class = "basis-1/2">{order.quantity}</div>
                        </div>
                    ))}
                </div>
                <div class="basis-3/18 flex items-center border-y-1">
                    <div>현재 가격: 15,000</div>
                </div>
                <div class="basis-7/18 border-1">
                    {buyOrder.map((order, index) => (
                            <div class= "flex">
                                <div class = "basis-1/2">{order.price}</div>
                                <div class = "basis-1/2">{order.quantity}</div>
                            </div>
                        ))}
                </div>
            </div>
            <div class ="flex flex-col basis-5/16 border-1">
                <div class="basis-4/22 flex items-center border-b-1">
                    <div>거래내역</div>
                </div>
                <div class= "flex basis-3/22 border-b-1">
                    <div class = "basis-1/2 pl-1 border-r-1" >가격</div>
                    <div class = "basis-1/2 pl-1" >수량</div>
                </div>
                <div class = "basis-15/22">
                    {tracnsactionHistory.map((tx, index) => (
                            <div class= "flex">
                                <div class = "basis-1/2">{tx.price}</div>
                                <div class = "basis-1/2">{tx.quantity}</div>
                            </div>
                        ))}
                </div>
            </div>
        </div>
    )
}