import axios from 'axios';

// 创建一个 axios 实例，配置基础 URL 和默认的请求头
const apiClient = axios.create({
  // 基础 URL，所有请求的 URL 都会基于这个基础 URL
  baseURL: 'http://localhost:8080/', // 替换为你的后端服务地址
  // 默认的请求头，所有请求都会带上这些头信息
  headers: {
    'Content-Type': 'application/json', // 请求的内容类型为 JSON
  },
});

// 导出一个函数，用于创建签到任务
// 参数：teacherId (教师 ID)，startTime (开始时间)，endTime (结束时间)
export const createSignInTask = (teacherId: number, startTime: string, endTime: string) => {
    // 使用 POST 方法发送请求，URL 为 '/teacher/createSignInTask'
    // 请求体包含 teacherId, startTime 和 endTime
    return apiClient.post('/teacher/createSignInTask', {
      teacherId,   // 教师 ID
      startTime,   // 开始时间
      endTime,     // 结束时间
    }).then(response => {
      // 假设返回的响应数据中包含一个字段 'signNumber'
      const signNumber = response.data;
     
      return signNumber;
    }).catch(error => {
      // 处理请求错误
      console.error('Error creating sign-in task:', error);
      throw error;
    });
  };

// 导出一个函数，用于查看签到结果
// 参数：taskId (任务 ID)
export const viewSignInResults = (taskId: number) => {
  // 使用 GET 方法发送请求，URL 为 '/teacher/viewSignInResults'
  // 请求参数包含 taskId
  return apiClient.get(`/teacher/viewSignInResults`, {
    params: { taskId }, // 任务 ID 作为查询参数
  });
};

// 导出一个函数，用于手动签到
// 参数：taskId (任务 ID)，studentId (学生 ID)，status (签到状态)
export const manualSignIn = (taskId: number, studentId: number, status: string) => {
  // 使用 POST 方法发送请求，URL 为 '/teacher/manualSignIn'
  // 请求体包含 taskId, studentId 和 status
  return apiClient.post('/teacher/manualSignIn', {
    taskId,     // 任务 ID
    studentId,  // 学生 ID
    status,     // 签到状态
  });
};


export const signIn = async (signNumber: number , studentId: number) => {

  return apiClient.post('/student/signIn',{
      signNumber,
      studentId,
  });
}

export const fetchSignInStatusApi = async (studentId: number ) => {


  return apiClient.get('/student/viewSignInStatus',{
        params:{studentId}
  });

};