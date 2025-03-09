import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import jwtDecode from 'jwt-decode';

const ProtectedRoute = ({ component: Component, ...rest }) => {
  // localStorage에서 토큰 추출
  const token = localStorage.getItem('accessToken');
  let isAuthenticated = false;

  if (token) {
    try {
      // 토큰을 디코딩하여 만료 시간(exp) 확인 (exp는 초 단위)
      const { exp } = jwtDecode(token);
      if (Date.now() < exp * 1000) {
        isAuthenticated = true;
      }
    } catch (error) {
      isAuthenticated = false;
    }
  }

  return (
    <Route
      {...rest}
      render={(props) =>
        isAuthenticated ? (
          // 인증된 사용자라면 해당 컴포넌트 렌더링
          <Component {...props} />
        ) : (
          // 인증되지 않은 경우 로그인 페이지로 리다이렉트
          <Redirect to="/login" />
        )
      }
    />
  );
};

export default ProtectedRoute;
