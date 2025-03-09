import React from 'react'
import { useEffect, useState } from 'react';
import TopMenu from '../../shared/components/TopBar'
import AccountComponent from '../account/Account'
import { getAccountInfo } from '../account/api/accountApi.js';
export default function HomePage() {
    const accountId = 1;
    const [accountInfo, setAccountInfo] = useState(null);
    const [loading, setLoading] = useState(true);
  
    useEffect(() => {
        getAccountInfo(accountId)
        .then((response) => {
            console.log(response)
          setAccountInfo(response.data);
          setLoading(false);
        })
        .catch((error) => {
          console.error("계좌 정보를 가져오는 중 오류 발생:", error);
          setLoading(false);
        });
    }, [accountId]);
  
    return (
      <div>
        <TopMenu />
        {loading ? (
          <p>계좌 정보를 불러오는 중...</p>
        ) : (
          <AccountComponent account={accountInfo} />
        )}
      </div>
    );
  }