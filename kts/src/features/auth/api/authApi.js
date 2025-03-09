import axiosInstanceNoAuth from "../../../shared/api/axiosInstanceNoAuth";

export const login = (credentials) => {
    return axiosInstanceNoAuth.post('/api/auth/login', credentials);
  }; 

export const register = (credentials) => {
  return axiosInstanceNoAuth.post('/api/auth/register', credentials);
}