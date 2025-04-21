<template>
  <section :class="['header', { 'header-scrolled': isScrolled }]">
    <div class="container">
      <div class="logo">
        <img src="https://www.itcast.cn/images/logo.png" alt="logo" />
        <span>传智教育学习平台</span>
      </div>
      <nav class="nav">

        <div class="nav-item" >系统通知</div>
        <div class="nav-item">待批作业</div>
        <div class="nav-item">待批考试</div>
        <div class="nav-item">试卷库</div>
        <div class="nav-item">实践库</div>
      </nav>
      <div class="user-info">
        <div class="dropdown" @click="toggleDropdown">
          <button class="dropbtn">
            <svg class="dropdown-icon" viewBox="0 0 24 24" width="24" height="24">
              <path d="M7 10l5 5 5-5z"></path>
            </svg>
            <img src="../../assets//teacher/teacher.png" alt="user-avatar" />
          </button>
          <div v-if="isDropdownOpen" class="dropdown-content">
            <div @click="navigateTo('teacher-profile')">个人中心</div>
            <!-- <div @click="navigateTo('change-password')">修改密码</div> -->
            <div>修改密码</div>
            <div @click="layout">退出</div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts" setup name="Header">
import { onMounted, onUnmounted, ref } from 'vue';
import { useRoute, useRouter } from "vue-router";

// import { defineProps } from "vue";


const router = useRouter();
//  拿到父组件Home1的接口内容
const route = useRoute();
const userData = route.query.userData ? JSON.parse(decodeURIComponent(route.query.userData as string)) : null;
console.log("@@@Header", userData);

const isScrolled = ref(false);
const isDropdownOpen = ref(false);

const handleScroll = () => {
  isScrolled.value = window.scrollY > 50;
};

const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};

const navigateTo = (path: string) => {
  // 这里可以使用 Vue Router 进行导航
  // console.log(`Navigating to ${path}`);
  const serializedUserData = encodeURIComponent(JSON.stringify(userData));
  // const serializedUserData = encodeURIComponent(JSON.stringify(response));
  router.push({
    path,
    //  将接口内容传到Profile组件中
    query: { userData: serializedUserData }
    // query: { response:serializedUserData }
  })

};

function layout(){
  router.push({
    path:'/login'
  })
  window.sessionStorage.removeItem("token")
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});



</script>

<style scoped>
.header {
  width: 100%;
  height: 60px;
  top: 0;
  left: 0;
  background-color: transparent;
  position: fixed;
  z-index: 1000;
  transition: background-color 0.3s, height 0.3s;
  background-color: #f8f9fa;
}

.header-scrolled {
  background-color: #f8f9fa;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  height: 50px;
}

.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  box-sizing: border-box;
}

.logo {
  display: flex;
  align-items: center;
}

.logo img {
  height: 40px;
  margin-right: 10px;
}

.logo span {
  font-size: 18px;
  font-weight: bold;
  letter-spacing: 0.5px;
}

.nav {
  display: flex;
  align-items: center;
}

.nav-item {
  margin: 0 15px;
  color: #333;
  font-size: 16px;
  font-weight: normal;
  position: relative;
  cursor: pointer;
  transition: color 0.3s, font-size 0.3s, font-weight 0.3s;
  letter-spacing: 0.5px;
  display: inline-block;
}

.nav-item::after {
  content: '';
  position: absolute;
  width: 0;
  height: 2px;
  display: block;
  margin-top: 5px;
  right: 0;
  background: #007bff;
  transition: width 0.2s ease;
}

.nav-item:hover::after {
  width: 100%;
  left: 0;
  right: auto;
}

.nav-item:hover {
  font-size: 18px;
  font-weight: bold;
}

.nav-item.active {
  color: #007bff;
  font-size: 18px;
  font-weight: bold;
}

.nav-item.active::after {
  width: 100%;
  left: 0;
  right: auto;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-info img {
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropbtn {
  background: none;
  border: none;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.dropbtn .dropdown-icon {
  margin-right: 10px;
}

.dropdown-icon {
  fill: #333;
  transition: transform 0.3s;
}

.dropdown-content {
  display: none;
  position: absolute;
  right: 0;
  background-color: #fff;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content div {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  cursor: pointer;
}

.dropdown-content div:hover {
  background-color: #f1f1f1;
}

.dropdown:hover .dropdown-content {
  display: block;
}

@media (max-width: 768px) {
  .nav-item {
    margin: 0 10px;
  }

  .logo span {
    display: none;
  }
}

@media (max-width: 576px) {
  .nav-item {
    margin: 0 5px;
    font-size: 14px;
  }

  .user-info img {
    height: 30px;
  }
}
</style>