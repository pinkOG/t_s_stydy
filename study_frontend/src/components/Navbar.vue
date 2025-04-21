<template>
  <div class="navbar">
    <div class="logo">CPU教学辅助平台</div>
    <div class="nav-links">
      <a v-for="(link, index) in links" :key="index" :class="{'active': index === activeIndex}" @click="navigate(index)">
        {{ link }}
      </a>
    </div>
    <div class="user-section">
      <img class="avatar" src="../assets/course1.jpg" alt="头像" />
      <div class="dropdown">
        <button @click="toggleDropdown">个人中心</button>
        <div v-if="showDropdown" class="dropdown-content">
          <a href="#">个人中心</a>
          <a href="#">修改个人信息</a>
          <a href="#">退出登录</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'Navbar',
  setup() {
    const links = ref(['首页', '课程', '通知', '设置']);
    const activeIndex = ref(0);
    const showDropdown = ref(false);

    const navigate = (index: number) => {
      activeIndex.value = index;
    };

    const toggleDropdown = () => {
      showDropdown.value = !showDropdown.value;
    };

    return {
      links,
      activeIndex,
      navigate,
      showDropdown,
      toggleDropdown
    };
  }
});
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #333;
  color: #fff;
}

.logo {
  font-size: 24px;
}

.nav-links a {
  margin: 0 15px;
  text-decoration: none;
  color: #fff;
  transition: color 0.3s;
}

.nav-links a.active {
  border-bottom: 2px solid #fff;
}

.nav-links a:hover {
  color: #ccc;
}

.user-section {
  display: flex;
  align-items: center;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.dropdown {
  position: relative;
}

.dropdown-content {
  display: block;
  position: absolute;
  right: 0;
  background-color: #fff;
  color: #000;
  min-width: 150px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
  background-color: #ddd;
}
</style>