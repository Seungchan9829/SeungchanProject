import React, { useState } from 'react';
import SellOrderForm from './SellOrderForm';
import BuyOrderForm from './BuyOrderForm';
import TradeHistoryForm from './TradeHistoryForm';
import { useSubmitOrder } from './hooks/useSubmitOrder';

export default function TradeOrderForm() {
    const [side, setSide] = useState('매수')
    const [symbol, setSymbol] = useState("AAPL")
    const {mutate : submitOrderMutate, isLodaing, isSuccess, isError, error} = useSubmitOrder();

    return(
        <div class = "flex flex-col border-1 w-122 h-119">
            <div class = "border-b-1 border-[rgb(212,214,220)] h-11">
                <ul class = "flex items-center h-full">
                    <li class={`basis-1/3 flex justify-center h-full items-center
                        ${side === '매수' ? 'border-b-2 border-red-500 font-bold' : ''}`}                         onClick = {() => setSide('매수')}>매수</li>
                    <li class={`basis-1/3 flex justify-center h-full items-center
                      ${side === '매도' ? 'border-b-2 border-blue-500 font-bold' : ''}`}
                      onClick = {() => setSide('매도')}>매도</li>
                    <li
                        className={`basis-1/3 flex justify-center h-full items-center cursor-pointer
                        ${side === '거래내역' ? 'border-b-2 border-gray-500 font-bold' : ''}`}
                        onClick={() => setSide('거래내역')}
                    >
                        거래내역
                    </li>
                </ul>
            </div>
                {side === '매수' && <BuyOrderForm />}
                {side === '매도' && <SellOrderForm />}
                {side === '거래내역' && <TradeHistoryForm/>}
        </div>
    )
}