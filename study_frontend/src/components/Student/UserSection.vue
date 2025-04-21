<template>
  <section class="user-section">
    <div class="user-card">
      <img src="../../assets/头像.png" alt="user-avatar" />
      <p>上午好，同学！厦门理工学院</p>
      <div class="progress-bar">
        <span>个人资料：</span>
        <div class="progress">
          <div class="progress-fill" :style="{ width: '80%' }"></div>
        </div>
        <span>80%</span>
      </div>
    </div>

    <div class="notifications">
      <div class="notification-item" @click="toSignIn">
        <img src="../../assets/student/签到 (1).png" alt="系统通知" />
        <span class="qiandao">我的签到</span>
      </div>

      <div class="notification-item" @click="showHomework">
        <img src="../../assets/student/作业.png" alt="我的作业" />
        <span>我的作业</span>
      </div>

      <div class="notification-item" @click="showMutualReview">
        <img src="../../assets/student/批改.png" alt="作业互改" />
        <span>作业互改</span>
      </div>

      <div class="notification-item">
        <img src="../../assets/student/设置.png" alt="系统设置" />
        <span>系统设置</span>
      </div>
    </div>

    <!-- 我的作业弹出框 -->
    <div v-if="homeworkVisible" class="homework-popup">
      <div class="homework-popup-content">
        <span class="close" @click="closeHomework">&times;</span>
        <h2>我的作业</h2>
        <ul>
          <li v-for="(homework, index) in homeworkList" :key="index">
            <div class="homework-item">
              <div class="homework-details" >
                <div class="homework-info">
                  <span>{{ homework[0] }}</span>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>

   
  </section>
</template>

<script lang="ts" setup name="UserSection">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import router from '../../router';

const homeworkVisible = ref(false);
const mutualReviewVisible = ref(false);
const homeworkList = ref([]);
const mutualReviewList = ref([]);
const studentId = 1;
const taskId = 6;

onMounted(() => {
  document.body.style.backgroundColor = "#F1F5F8";
})

function toSignIn() {
  router.push({
    name: 'StudentSignIn'
  })
}

function showHomework() {
  homeworkVisible.value = true;
  fetchHomework();
}

function closeHomework() {
  homeworkVisible.value = false;
}

function showMutualReview() {
  mutualReviewVisible.value = true;
  fetchMutualReview();
}

function closeMutualReview() {
  mutualReviewVisible.value = false;
}

async function fetchHomework() {
  try {
    const response = await axios.post('http://localhost:8080/student/getAllHomeworkList', null, {
      params: {
        studentId
      }
    });
    homeworkList.value = response.data;
    console.log("@@UserSection", homeworkList.value);
  } catch (error) {
    console.error('Error fetching homework data:', error);
  }
}

async function fetchMutualReview() {
  try {
    const response = await axios.post('http://localhost:8080/student/mutualReview', null, {
      params: {
        studentId,
        taskId
      }
    });
    mutualReviewList.value = response.data;
    console.log("@@UserSection", mutualReviewList.value);
  } catch (error) {
    console.error('Error fetching mutual review data:', error);
  }
}

async function submitReview(review) {
  try {
    await axios.post('http://localhost:8080/student/submitReview', {
      studentId,
      review
    });
    alert('评价提交成功');
  } catch (error) {
    console.error('Error submitting review:', error);
    alert('提交评价失败');
  }
}
</script>

<style scoped>
/* .qiandao:hover {
  background-color: green;
} */

.user-section {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 1266px;
  height: 182px;
  margin: 0 auto;
  margin-top: 20px;
  margin-bottom: 30px;
  padding: 20px;
  box-sizing: border-box;
  background-color: #ffffff;
  border-radius: 16px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.user-card {
  display: flex;
  align-items: center;
  background-color: #fff;
  padding: 10px;
  border-radius: 8px;
  flex: 1 0 auto;
  margin-right: 20px;
}

.user-card img {
  height: 44px;
  border-radius: 50%;
  margin-right: 10px;
}

.user-card p {
  margin: 0;
  font-size: 14px;
  font-weight: bold;
}

.progress-bar {
  display: flex;
  align-items: center;
  margin-left: 10px;
}

.progress-bar span {
  margin-right: 5px;
  font-size: 12px;
}

.progress {
  width: 100px;
  height: 10px;
  background-color: #e9ecef;
  border-radius: 5px;
  overflow: hidden;
  margin-right: 5px;
}

.progress-fill {
  height: 100%;
  background-color: #007bff;
}

.notifications {
  display: flex;
  justify-content: space-between;
  flex: 1 0 auto;
  gap: 10px;
}

.notification-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #fff;
  padding: 10px;
  border-radius: 8px;
  position: relative;
  flex: 1 0 0;
}

.notification-item img {
  width: 44px;
  height: 44px;
  margin-bottom: 5px;
}

.notification-item span {
  text-align: center;
  font-size: 12px;
}

.homework-popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.homework-popup-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  width: 500px;
  max-height: 80%;
  overflow-y: auto;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  position: relative;
}

.homework-popup .close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  cursor: pointer;
}

.homework-item {
  border-bottom: 1px solid #ddd;
  padding: 10px 0;
  text-align: left;
}

.homework-title {
  font-weight: bold;
  font-size: 16px;
  margin-bottom: 5px;
}

.homework-details {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.homework-info {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
}

.homework-info span:first-child {
  font-weight: bold;
}

.homework-info textarea {
  width: 100%;
  height: 50px;
}

.homework-info input[type='number'] {
  width: 50px;
}
</style>