import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

const AccountComponent = ({ account }) => {

  return (
    <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
      <Card sx={{ maxWidth: 400, width: '100%' }}>
        <CardContent>
          <Typography variant="h5" component="div">
            계좌 번호: {account.accountNumber}
          </Typography>
          <Typography sx={{ mt: 1.5 }} color="text.secondary">
            잔액: {Number(account.accountBalance).toLocaleString()} 원
          </Typography>
          {/* {account.user && (
            <Typography variant="body2" sx={{ mt: 1 }}>
              소유자: {account.user.userName}
            </Typography>
          )} */}
        </CardContent>
        <CardActions>
          <Button variant="contained" color="primary">
            입금
          </Button>
          <Button variant="contained" color="secondary">
            출금
          </Button>
        </CardActions>
      </Card>
    </Box>
  );
};

export default AccountComponent;
