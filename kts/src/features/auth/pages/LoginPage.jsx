import React from 'react'
import { Container, Box, Typography, TextField, Button } from '@mui/material';
import { login } from '../api/authApi';
import { saveTokenLocalStorage } from '../../../shared/jwt/jwtUtils';
import { useNavigate } from 'react-router-dom';

export default function LoginPage() {
  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    // 로그인 처리 로직을 여기에 추가합니다.
    login({
      "userEmail" : email,
      "password" : password,
    }).then(response => {
      const {token} = response.data;
      if(saveTokenLocalStorage(token)){
        navigate('/')
      }
        
    }).catch(error => {
      if(error.response){
        if(error.response.status == 401){
          alert("로그인에 실패하였습니다.")
        }
      }
    })
    console.log({ email, password });
  };
  return (
    <Container maxWidth="sm">
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center'
        }}
      >
        <Typography component="h1" variant="h5">
          로그인
        </Typography>
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="이메일 주소"
            name="email"
            autoComplete="email"
            autoFocus
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="비밀번호"
            type="password"
            id="password"
            autoComplete="current-password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            로그인
          </Button>
        </Box>
      </Box>
    </Container>
  );
}
