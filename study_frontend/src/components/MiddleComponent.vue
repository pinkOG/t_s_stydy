<template>
  <div class="middle">
    <div class="welcome">
      <img src="../assets/course1.jpg" alt="头像" class="avatar">
      <p>欢迎语</p>
    </div>
    <div class="components">
      <button @click="currentComponent = '公告'">
        <img src="../assets/course1.jpg" alt="Announcements" class="icon">
        公告
      </button>
      <button @click="currentComponent = '学习中心'">
        <img src="../assets/course1.jpg" alt="Learning Center" class="icon">
        学习中心
      </button>
      <button @click="currentComponent = '主页'">
        <img src="../assets/course1.jpg" alt="Homepage" class="icon">
        主页
      </button>
      <button @click="currentComponent = '系统通知'">
        <img src="../assets/course1.jpg" alt="System Notifications" class="icon">
        系统通知
      </button>
      <p>当前组件: {{ currentComponent }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MiddleComponent',
  data() {
    return {
      currentComponent: '公告'
    }
  }
}
</script>

<style scoped>
.middle {
  padding: 20px;
  background-color: #f0f0f0;
}

.welcome {
  display: flex;
  align-items: center;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.components {
  margin-top: 20px;
}

button {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 10px;
  padding: 10px;
  border: none;
  background: none;
  cursor: pointer;
}

.icon {
  width: 50px;
  height: 50px;
  margin-bottom: 5px;
}
</style>