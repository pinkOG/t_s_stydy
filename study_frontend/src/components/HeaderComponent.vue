<template>
  <header class="header">
    <div class="nav-links">
      <a href="#" class="nav-link">导航链接1</a>
      <a href="#" class="nav-link">导航链接2</a>
      <a href="#" class="nav-link">导航链接3</a>
    </div>
    <div class="profile">
      <img src="../assets/course1.jpg" alt="头像" class="avatar">
      <div class="dropdown">
        <button class="dropdown-toggle">系统通知</button>
        <div class="dropdown-menu">
          <a href="#">通知1</a>
          <a href="#">通知2</a>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'HeaderComponent'
}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #f8f8f8;
}

.nav-links {
  display: flex;
}

.nav-link {
  margin-right: 15px;
  text-decoration: none;
  color: #000;
}

.profile {
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

.dropdown-toggle {
  background: none;
  border: none;
  cursor: pointer;
}

.dropdown-menu {
  display: none;
  position: absolute;
  right: 0;
  background-color: #fff;
  border: 1px solid #ccc;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.dropdown:hover .dropdown-menu {
  display: block;
}
</style>