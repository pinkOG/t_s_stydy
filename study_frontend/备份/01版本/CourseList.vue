<template>
    <div class="course-list">
      <div class="course-header">
        <span>在学课程</span>
        <span>已结束课程</span>
      </div>
      <div class="course-content">
        <div class="course-item add-course">
          <i class="el-icon-plus"></i>
          <span>添加课程</span>
        </div>
        <div class="course-item">
          <img src="../assets/course1.jpg" alt="课程图片" />
          <span>Java企业级别开发</span>
        </div>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  
  export default defineComponent({
    name: 'CourseList',
  });
  </script>
  
  <style scoped>
  .course-list {
    padding: 20px;
    background-color: #f2f2f2;
  }
  
  .course-header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  
  .course-content {
    display: flex;
  }
  
  .course-item {
    width: 200px;
    height: 200px;
    background-color: white;
    margin-right: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    cursor: pointer;
  }
  
  .course-item img {
    width: 100%;
    height: 80%;
  }
  
  .add-course {
    background-color: #e6e6e6;
  }
  
  .add-course i {
    font-size: 40px;
  }
  </style>