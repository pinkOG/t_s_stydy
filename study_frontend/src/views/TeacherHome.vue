<template>
    <div class="teacher-home">
      <h1>教师端主页</h1>
      <div class="section">
        <h2>视频、课件、作业上传</h2>
        <input type="file" @change="uploadFile" />
      </div>
      <div class="section">
        <h2>设定签到</h2>
        <button @click="setSignIn">设定签到</button>
      </div>
      <div class="section">
        <h2>作业发布和设定互改功能</h2>
        <button @click="publishHomework">发布作业</button>
      </div>
    </div>
  </template>
  
  <script setup lang="ts" name="TeacherHome">
  //import { ref } from 'vue';
  import axios from 'axios';
  
  const uploadFile = (event: Event) => {
    const files = (event.target as HTMLInputElement).files;
    if (files && files.length > 0) {
      const formData = new FormData();
      formData.append('file', files[0]);
      axios.post('/api/upload', formData)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    }
  };
  
  const setSignIn = () => {
    axios.post('/api/signin')
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  };
  
  const publishHomework = () => {
    axios.post('/teacher/publishHomeworkAndSave')
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  };
  </script>
  
  <style scoped>
  .teacher-home {
    padding: 20px;
  }
  .section {
    margin-bottom: 20px;
  }
  </style>