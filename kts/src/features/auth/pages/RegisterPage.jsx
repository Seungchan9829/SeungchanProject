import React from 'react'
import { Container, Box, Typography, TextField, Button } from '@mui/material';
import { saveTokenLocalStorage } from '../../../shared/jwt/jwtUtils';
import { register } from '../api/authApi';
import { useNavigate } from 'react-router-dom';

const RegisterPage = () => {
  const navigate = useNavigate();
  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [userName, setUserName] = React.useState('');
  const handleSubmit = (event) => {
    event.preventDefault();
    // 로그인 처리 로직을 여기에 추가합니다.
    register({
      "userEmail" : email,
      "password" : password,
      "userName" : userName
    }).then(response => {
      const {token} = response.data;
      // jwt 토큰을 로컬스토리지에 저장하는 함수.
      saveTokenLocalStorage(token)
      alert("회원가입이 완료되었습니다.")
      // 홈으로 리다이렉트
      navigate('/')
    }).catch(error => {
      console.log(error)
      if(error.response) {
        if(error.response.status == 409){
          alert("중복된 이메일입니다.")
        }
      }
    })
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
          회원가입
        </Typography>
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 1 }}>
        <TextField
            margin="normal"
            required
            fullWidth
            id="userName"
            label="이름"
            name="userName"
            autoComplete="userName"
            autoFocus
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
          />
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
            회원가입
          </Button>
        </Box>
      </Box>
    </Container>
  );
}

export default RegisterPage
