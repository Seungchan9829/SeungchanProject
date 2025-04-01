import React from 'react';
import { validateToken } from '../jwt/jwtUtils';
import { Navigate } from 'react-router-dom';
const ProtectedRoute = ({ children }) => {
  // localStorage에서 토큰 추출. 없을 경우 null
  const token = localStorage.getItem('token')

  // 토큰 시간 유효성 검사
  if (!validateToken(token)){
    return <Navigate to = "/login" replace/>
  }
  // 서버에서 유효성 검사?

  return children
};

export default ProtectedRoute;
