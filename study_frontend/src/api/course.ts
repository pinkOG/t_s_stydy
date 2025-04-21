// src/api/course.ts
import axios from 'axios';

export const getCourses = () => {
  // return axios.get('/api/courses');
  return [
    {
      id: 1,
      name: 'Java企业级别开发',
      image: '@/assets/course1.jpg',
    },
  ];
};