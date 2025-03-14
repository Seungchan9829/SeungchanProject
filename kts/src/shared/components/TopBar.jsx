import React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { useNavigate, Link } from 'react-router-dom';

const TopMenu = () => {
    const navigate = useNavigate();
    // 유저 정보를 가지고 오는 훅

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          {/* 로고 혹은 앱 이름 */}
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            My App
          </Typography>
          {/* 메뉴 버튼 */}
          <Button onClick={() => navigate("/home")} color="inherit">Home</Button>
      <Button onClick={() => navigate("/login")} color="inherit">로그인</Button>
      <Button onClick={() => navigate("/register")} color="inherit">회원가입</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default TopMenu;
