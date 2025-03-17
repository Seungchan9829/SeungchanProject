import React, {useState} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import useGetAccount from './useGetAccount';
import Divider from '@mui/material/Divider';
import { useNavigate } from 'react-router-dom';
import useTransactionProcess from './useTransactionProcess';

const AccountComponent = ({ account }) => {
  const naviagte = useNavigate();
  const [accountId, setAccountId] = useState(1)
  const accountInfo = useGetAccount(accountId)

  console.log(accountInfo)
  const mutation = useTransactionProcess({ accountId, type: "Deposit", amount: 10000 });

  return (
    <Box sx={{ display: 'flex', flexDirection : 'column', mt: 4, height: '370px' }}>
      <Card  sx={{ maxWidth: 400, width: '100%', height : '80%' }}>
        <CardContent>
          <Typography variant="h5" component="div">
            계좌 번호: {accountInfo ? accountInfo.accountNumber : ''}
          </Typography>
          <Typography sx={{ mt: 1.5 }} color="text.secondary">
            잔액: {accountInfo ? Number(accountInfo.accountBalance).toLocaleString() : 0} 원
          </Typography>
        </CardContent>
        <CardActions>
          <Button onClick={() => { mutation.mutate();}} variant="contained" color="primary">
            입출금
          </Button>
        </CardActions>
      </Card>

      <Divider/>
      <Button onClick = {() => naviagte('/account-management')}variant='contained'>계좌 관리</Button>
    </Box>
  );
};

export default AccountComponent;
