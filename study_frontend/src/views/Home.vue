<template>
  <div class="homeBox">
    <div class="home-container">
      <div class="header">
        <h1>欢迎来到智慧教育系统</h1>
        <p>传智播客版</p>
      </div>
      <div class="features">
        <div class="feature">
          <img src="/src/resourses/video-icon.png" alt="Video Icon">
          <h2>Interactive Video Lessons</h2>
          <p>Engaging video content for effective learning experiences.</p>
        </div>
        <div class="feature">
          <img src="/src/resourses/homework-icon.png" alt="Homework Icon">
          <h2>Homework Assignments</h2>
          <p>Teachers can assign and manage homework effortlessly.</p>
        </div>
        <div class="feature">
          <img src="/src/resourses/grade-icon.png" alt="Grade Icon">
          <h2>Grading System</h2>
          <p>Efficiently grade student submissions and track progress.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" >
import { onMounted } from "vue";
onMounted(() => {
  document.body.style.backgroundColor = "#cadcd4";
})
</script>

<style scoped>
/* .homeBox {
		position: fixed;
		width: 100%;
		height: 100%;
		top: 0px;
		background-color: #666;
    margin-top: 70px;
	} */
.home-container {
  text-align: center;
  padding: 50px;
}

.header {
  margin-bottom: 50px;
}

.header h1 {
  font-size: 36px;
  color: #333;
}

.header p {
  font-size: 20px;
  color: #666;
}

.features {
  display: flex;
  justify-content: center;
}

.feature {
  width: 300px;
  padding: 20px;
  margin: 0 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.feature:hover {
  transform: translateY(-5px);
}

.feature img {
  width: 80px;
  height: 80px;
  margin-bottom: 20px;
}

.feature h2 {
  font-size: 24px;
  margin-bottom: 10px;
  color: #333;
}

.feature p {
  font-size: 16px;
  color: #666;
}
</style>
