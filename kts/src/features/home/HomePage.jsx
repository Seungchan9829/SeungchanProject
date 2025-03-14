import React from 'react'
import { useEffect, useState } from 'react';
import TopMenu from '../../shared/components/TopBar'
import AccountComponent from '../account/Account'
import { getAccountInfo } from '../account/api/accountApi.js';
import { Container, Box} from '@mui/material';
export default function HomePage() {
    const accountId = 1;
    const [accountInfo, setAccountInfo] = useState(null);
    const [loading, setLoading] = useState(false);
  
    // useEffect(() => {
    //     getAccountInfo(accountId)
    //     .then((response) => {
    //         console.log(response)
    //       setAccountInfo(response.data);
    //       setLoading(false);
    //     })
    //     .catch((error) => {
    //       console.error("계좌 정보를 가져오는 중 오류 발생:", error);
    //       setLoading(false);
    //     });
    // }, [accountId]);
  
    return (
      <div>
        <Container maxWidth="sm">
          <Box sx={{ bgcolor: '#cfe8fc', height: '50vh' }} />
        </Container>
        <Container maxWidth= "Lg" sx = {{m: 2, display : 'flex', flexDirection: 'row', gap: 2}}>
          <Box sx={{flex : 3, bgcolor : 'primary.main', p: 2}}>
            hi
          </Box>
          <Box sx={{ flex : 1, bgcolor : 'primary.main', p: 2, ml: 'auto'}}>
          {loading ? (
          <p>계좌 정보를 불러오는 중...</p>
        ) : (
          <AccountComponent account={accountInfo} />
        )}
          </Box>
        </Container>

      </div>
    );
  }