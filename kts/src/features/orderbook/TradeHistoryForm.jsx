import React from 'react';
import { useState } from 'react';
import OrderHistoryTable from './OrderHistoryTable';
import TradeHistoryTable from './TradeHistoryTable';
export default function TradeHistoryForm() {
    const [tab, setTab] = useState('미체결');
    return(
        <div class ="flex-col h-122 pt-2">
      {/* 상단 탭 + 드롭다운 */}
      <div className="flex items-center justify-between border-b px-3 pb-2">
        <div className="flex items-center space-x-4">
          <label className="flex items-center space-x-1">
            <input
              type="radio"
              name="history"
              value="미체결"
              checked={tab === '미체결'}
              onChange={() => setTab('미체결')}
            />
            <span>미체결</span>
          </label>
          <label className="flex items-center space-x-1">
            <input
              type="radio"
              name="history"
              value="체결"
              checked={tab === '체결'}
              onChange={() => setTab('체결')}
            />
            <span>체결</span>
          </label>
        </div>
      </div>

        {tab === '미체결' ? <OrderHistoryTable/> : <TradeHistoryTable/>}
    </div>
    )
}
