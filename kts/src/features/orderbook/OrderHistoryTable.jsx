import React from 'react';

export default function OrderHistoryTable() {
    return(
        <div>
                        {/* 테이블 헤더 */}
            <div className="grid grid-cols-4 text-center text-xs text-gray-500 border-b px-2 py-2 h-12">
                <div className="flex items-center justify-center">주문시간</div>
                <div className="flex items-center justify-center">구분</div>
                <div className="flex items-center justify-center">주문가격</div>
                <div>주문량<br />미체결량</div>
            </div>

            {/* 내용 없음 */}
            <div className="flex flex-col items-center justify-center h-48 text-gray-400 text-sm">
                <svg xmlns="http://www.w3.org/2000/svg" className="w-8 h-8 mb-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={1.5} d="M9 17v-6h6v6h5v2H4v-2h5zm6-8V7a3 3 0 00-6 0v2h6z" />
                </svg>
                <div> 내역이 없습니다.</div>
            </div>
        </div>
    )
} 