<template>
    <el-header>
      <div class="navbar">
        <div class="logo">CPU教辅平台</div>
        <el-menu :default-active="active" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1">首页</el-menu-item>
          <el-menu-item index="2">课程</el-menu-item>
          <el-menu-item index="3">系统通知</el-menu-item>
          <el-menu-item index="4">关于我们</el-menu-item>
        </el-menu>
        <div class="user-info">
          <el-avatar src="../assets/avatar.jpg"></el-avatar>
          <el-dropdown>
            <span class="el-dropdown-link">
              用户名<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>修改个人信息</el-dropdown-item>
              <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </el-header>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  
  export default defineComponent({
    name: 'Navbar',
    data() {
      return {
        active: '1',
      };
    },
  });
  </script>
  
  <style scoped>
  .navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #409EFF;
    padding: 0 20px;
  }
  
  .logo {
    font-size: 20px;
    color: white;
  }
  
  .user-info {
    display: flex;
    align-items: center;
  }
  
  .el-avatar {
    margin-right: 10px;
  }
  </style>