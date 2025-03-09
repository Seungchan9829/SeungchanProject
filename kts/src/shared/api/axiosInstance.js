import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', 
  timeout: 10000, 
  headers: {
    'Content-Type': 'application/json',
    Accept: 'application/json',
  },
});

api.interceptors.request.use(
  (config) => {
    // localStorage에서 JWT 토큰 가져오기
    const token = localStorage.getItem('token');
    if (token) {
      // Authorization 헤더에 토큰 설정 (Bearer 스킴 사용)
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // 요청 오류가 발생하면 Promise.reject로 에러 전파
    return Promise.reject(error);
  }
);

export default api