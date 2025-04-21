<template>
  <div class="big">
    <div class="user-profile">
      <h1>个人中心</h1>
      <div class="profile-info">
        <img src="../../assets/student/头像.png" alt="User Avatar" class="avatar" />
        <h2>{{ userData.data.user.username }}</h2>
        <p>学校：{{ userData.data.studentDetails.schoolName }}</p>
        <p>班级：{{ userData.data.studentDetails.studentClass }}</p>
        <p>专业：{{ userData.data.studentDetails.major }}</p>
        <p>学号(studentId)：{{ userData.data.studentDetails.studentId }}</p>
        <p>邮箱：{{ userData.data.user.email }}</p>
        <!-- <h2>{{ response.user.username }}</h2>
      <p>{{ response.user.userId }}</p> -->
      </div>
      <div class="profile-actions">
        <button @click="editProfile">退出登录</button>
        <button @click="logout">返回首页</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" name="Profile" setup>
import { onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';

// interface User {
//   name: string;
//   email: string;
//   avatarUrl: string;
// }

const router = useRouter();

const route = useRoute();
const userData = route.query.userData ? JSON.parse(decodeURIComponent(route.query.userData as string)) : null;
// const response = route.query.response ? JSON.parse(decodeURIComponent(route.query.response as string)) : null;
console.log("@@@profile", userData);

// const user: User = {
//   name: '张三',
//   email: 'zhangsan@qq.com',
//   avatarUrl: '../../assets/student/头像.png'
// };

const editProfile = () => {
  // 这里可以添加编辑资料的逻辑
  console.log('退出登录');
  router.push('/login')
};

const logout = () => {
  // 这里可以添加退出登录的逻辑
  console.log('返回首页');
  router.push('/home1'); // 假设你有一个 '/login' 路径
};
onMounted(() => {
  document.body.style.backgroundColor = "#F1F5F8";
})


</script>

<style scoped>
.user-profile {
  padding: 20px;

}

.profile-info {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.avatar {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}

.profile-actions {
  margin-top: 20px;
}

button {
  margin: 5px;
}
</style>