import React, {useState} from 'react';
import useGetAccount from './useGetAccount';
import { useNavigate } from 'react-router-dom';
import useTransactionProcess from './useTransactionProcess';
import { Box, Card, CardContent, CardActions, Typography, Button, Divider, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import useGetAccounts from './useGetAccounts';

const AccountComponent = () => {
  const navigate = useNavigate();
  const [accountId, setAccountId] = useState(null)

  // const mutation = useTransactionProcess({ accountId, type: "Deposit", amount: 10000 });

  const accounts = useGetAccounts() || [];

  const handleSelectChange = (event) => {
    setAccountId(event.target.value)
  }

  const selectedAccount = accounts.find((acc) => acc.id == accountId);
  return (
    <Box sx={{ display: 'flex', flexDirection : 'column', mt: 4, height: '370px' }}>
      <Typography>계좌 선택</Typography>
      <FormControl sx={{ mb: 2, width: 300 }}>
        <InputLabel id="account-select-label"></InputLabel>
        <Select
          value={accountId}
          onChange={handleSelectChange}
        >
          {accounts.map((acc) => (
            <MenuItem key={acc.id} value={acc.id}>
              {acc.accountNumber}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
       
      <Card  sx={{ maxWidth: 400, width: '100%', height : '80%' }}>
        <CardContent>
          <Typography variant="h5" component="div">
            계좌 번호: {selectedAccount ? selectedAccount.accountNumber : ''}
          </Typography>
          <Typography sx={{ mt: 1.5 }} color="text.secondary">
            잔액: {selectedAccount  ? Number(selectedAccount .accountBalance).toLocaleString() : 0} 원
          </Typography>
        </CardContent>
        <CardActions>
          <Button variant="contained" color="primary">
            입출금
          </Button>
        </CardActions>
      </Card>

      <Divider/>
      <Button onClick = {() => navigate('/account-management')}variant='contained'>계좌 관리</Button>
    </Box>
  );
};

export default AccountComponent;
