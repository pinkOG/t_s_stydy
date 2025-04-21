// src/api/user.ts
import axios from 'axios';

export const getUserInfo = () => {
  // return axios.get('/api/user/info');
  return {
    name: '用户名',
    avatar: '@/assets/avatar.jpg',
  };
};