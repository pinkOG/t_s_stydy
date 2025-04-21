<template>
  <div class="register-container">
    <h1>注册页面</h1>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="username" required />
        <p v-if="usernameExists" class="error-message">用户名已存在</p>
      </div>
      <div class="form-group">
        <label for="name">姓名:</label>
        <input type="text" id="name" v-model="name" required />
      </div>
      <div class="form-group">
        <label for="email">邮箱地址:</label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <div class="form-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="confirmPassword">确认密码:</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required />
      </div>
      <div class="form-group">
        <label for="role">选择端:</label>
        <select id="role" v-model="role">
          <option value="student">学生端</option>
          <option value="teacher">教师端</option>
        </select>
      </div>
      <button type="submit">注册</button>
    </form>
    <p v-if="errorMessage && !usernameExists" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref,onMounted } from 'vue';
import axios from 'axios';

const username = ref('');
const name = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const role = ref('student');
const errorMessage = ref('');
const usernameExists = ref(false);

const handleRegister = async () => {
  if (password.value !== confirmPassword.value) {
    errorMessage.value = '密码不匹配，请重新输入';
    usernameExists.value = false;
    return;
  }

  try {
    const response = await axios.post('http://localhost:8080/api/register', {
      username: username.value,
      name: name.value,
      email: email.value,
      password: password.value,
      role: role.value,
    });

    // 根据返回的内容处理逻辑
    if (response.data.success) {
      // 注册成功，重定向或其他操作
      console.log('注册成功');
    } else if (response.data.error === 'USERNAME_EXISTS') {
      // 用户名已存在
      usernameExists.value = true;
      errorMessage.value = '';
    } else {
      errorMessage.value = '注册失败，请重试';
      usernameExists.value = false;
    }
  } catch (error) {
    errorMessage.value = '注册过程中出现错误，请稍后再试';
    usernameExists.value = false;
  }
};
onMounted(() =>{
  document.body.style.backgroundColor="#cadcd4";
})
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 100px auto;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.8); /* 半透明背景 */
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h1 {
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 5px;
  color: #555;
}

input[type="text"],
input[type="password"],
input[type="email"],
select {
  width: 100%;
  padding: 10px;
  margin: 5px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #45a049;
}

.error-message {
  color: red;
  margin-top: 5px;
  font-size: 14px; /* 设置略小的字体 */
}
</style>