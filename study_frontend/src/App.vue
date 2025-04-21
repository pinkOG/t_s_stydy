<template>
  <div id="app">
    <nav class="navbar">
      <div class="navbar-links">
        <router-link to="/home" class="nav-link">Home</router-link>
        <router-link to="/login" class="nav-link">Login</router-link>
        <router-link to="/register" class="nav-link">Register</router-link>
        <!-- <router-link to="/teacher" class="nav-link">Teacher</router-link>
        <router-link to="/student" class="nav-link">Student</router-link> -->
      </div>
    </nav>
    <router-view />
  </div>
</template>

<script setup>

</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}

.navbar {
  display: flex;
  justify-content: flex-end;
  /* 将导航链接放置在右侧 */
  padding: 20px;
  background-color: #f8f9fa;
  /* 背景色 */
}

.navbar-links {
  display: flex;
}

.navbar-links .nav-link {
  font-weight: bold;
  font-size: 16px;
  /* 调整字体大小 */
  color: #2c3e50;
  margin-left: 15px;
  text-decoration: none;
}

.navbar-links .nav-link.router-link-exact-active {
  color: #42b983;
}</style>