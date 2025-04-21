<template>
  <div class="login-container">
    <h1>登录页面</h1>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="role">选择端:</label>
        <select id="role" v-model="role">
          <option value="student">学生端</option>
          <option value="teacher">教师端</option>
        </select>
      </div>
      <button type="submit">登录</button>
    </form>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script setup lang="ts" name="Login">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import router from '../router'

const username = ref('')
const password = ref('')
const role = ref('student')
const errorMessage = ref('')

const handleLogin = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/login', {
      username: username.value,
      password: password.value,
      role: role.value,
    })

    // 根据返回的内容处理逻辑

    if (response.data.role == 'STUDENT' && response.data.success) {
      // 登录成功，重定向或其他操作
      console.log('登录成功')
      //  response.user.username
      //  response.user.password
      window.sessionStorage.setItem('token', response.data.user.password)

      const encodedUserData = encodeURIComponent(JSON.stringify(response))

      router.push({
        name: 'Home1',
        //  将这个接口的内容传到Home1组件
        // query: { response }
        query: { userData: encodedUserData },
      })
    } else if (response.data.role == 'TEACHER' && response.data.success) {
      const encodedUserData = encodeURIComponent(JSON.stringify(response))
      window.sessionStorage.setItem('token', response.data.user.password)

      router.push({
        name: 'TeacherHome1',
        //  将这个接口的内容传到Home1组件
        // query: { response }
        query: { userData: encodedUserData },
      })
    } else {
      errorMessage.value = '账号或密码错误'
    }
  } catch (error) {
    errorMessage.value = '登录过程中出现错误，请稍后再试'
  }
}

onMounted(() => {
  document.body.style.backgroundColor = '#cadcd4'
})
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  background-image: url('/path/to/your/background-image.jpg');
  /* 替换为你的背景图片路径 */
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
}

.login-container {
  max-width: 400px;
  margin: 100px auto;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.8);
  /* 半透明背景 */
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

input[type='text'],
input[type='password'],
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
  background-color: #4caf50;
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
  margin-top: 20px;
  font-size: 14px;
  /* 设置略小的字体 */
}
</style>
