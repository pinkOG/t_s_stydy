<template>
  <section class="courses-section">
    <div>
      <div class="tabs">
        <span style="margin-right: 20px;cursor: pointer;" :class="{ active: activeTab === 'activeCourses' }"
          @click="activeTab = 'activeCourses'">在学课程</span>
        <span style="cursor: pointer;" :class="{ active: activeTab === 'archivedCourses' }"
          @click="activeTab = 'archivedCourses'" class="tab">已结束课程</span>
      </div>
      <div v-if="activeTab === 'activeCourses'" class="course-container">
        <div class="course-card add-course" @click="showAddCourseModal = true">
          <div class="add-icon">+</div>
          <div>添加课程</div>
        </div>
        <div v-for="course in courses" :key="course.code" class="course-card">
          <img :src="course.uriLink" alt="Course Image" class="course-image"
            @click="showCourseDetails(course.teacherCourse.courseId)">
          <div>课程：{{ course.teacherCourse.courseName }}</div>
          <div>课程ID：{{ course.teacherCourse.courseId }}</div>
        </div>
      </div>
      <div v-else class="course-container">
        <!-- 这里可以放置已归档课程的内容 -->
        <div>暂无已结束课程</div>
      </div>

      <!-- 添加课程弹出框 -->
      <div v-if="showAddCourseModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="showAddCourseModal = false">&times;</span>
          <h2>添加课程</h2>
          <input v-model="courseCode" placeholder="输入加课码">
          <button @click="addCourse">确认添加</button>
        </div>
      </div>

      <!-- 作业列表、作业分析 弹出框 -->
      <div v-if="showCourseModal" class="modal">
        <div class="modal-content">
          <!-- 三个功能按钮 -->
          <span class="close" @click="showCourseModal = false">&times;</span>
          <div class="button-row">
            <button @click="showHomeworkList = true; showHomeworkAnalysis = false; showscore = false">作业列表</button>
            <button @click="handleHomeworkAnalysis(selectedCourseId)">作业分析</button>
            <button @click="showAllScore(selectedCourseId)">学习资料</button>
          </div>

          <div v-if="showHomeworkList">
            <h2>作业列表</h2>
            <ul v-if="homeworkList.length" class="homework-list">
              <li v-for="homework in homeworkList" :key="homework.id" class="homework-item">
                <div class="homework-description">作业标题：{{ homework.assignmentsTasks.description }}</div>
                <div class="homework-details">
                  <span>截止时间：{{ homework.assignmentsTasks.endTime }}</span>
                  <span>任务ID：{{ homework.assignmentsTasks.taskId }}</span>
                  <a :href="homework.uriLink" target="_blank">下载链接</a>
                </div>
                <input type="file" @change="onFileChange($event, homework.assignmentsTasks.taskId)" />
                <button @click="submitHomework(homework.assignmentsTasks.taskId)">提交作业</button>
                <button @click="showMutualReview(homework.assignmentsTasks.taskId)" style="margin-left: 45px;">作业互改</button>
              </li>
            </ul>
            <div v-else>
              暂无作业
            </div>
          </div>
          <div v-if="showHomeworkAnalysis" class="homework-analysis">
            <h2>作业分析</h2>
            <div v-for="(detail, index) in scoreAnalysisDetails" :key="index" class="analysis-card">
              <p>作业ID：{{ detail.taskId }}</p>
              <p>最高成绩：{{ detail.highestScore }}</p>
              <p>最低成绩：{{ detail.lowestScore }}</p>
              <p>平均成绩：{{ detail.averageScore }}</p>
              <p>最终成绩：{{ detail.finalScore }}</p>
            </div>
          </div>

          <div v-if="showscore">
            <!-- 学习资料 展示 -->
            <p>学习资料</p>
            <div v-for="(score, index) in AllScore" :key="index">
              
              <span style="margin-left: 20px;">作业描述：{{ score.learningMaterial.description }}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <span>作业标题：{{ score.learningMaterial.title }}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <span><a :href="score.uriLink"> 下载链接</a></span>
              
              
            </div>
          </div>
        </div>
      </div>

      <!-- 作业互改弹出框 -->
      <div v-if="showMutualReviewModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="showMutualReviewModal = false">&times;</span>
          <h2>作业互改</h2>

          <div v-if="mutualReviewData.length" v-for="(mutualReviewData, index) in mutualReviewData"
            :key="mutualReviewData.reviewRecord.studentId">

            <div>互改链接：<a :href="mutualReviewData.uriLink" target="_blank">{{ mutualReviewData.uriLink }}</a></div>
            <!-- {{ mutualReviewData }} -->
            <div>
              <label for="feedback">作业评价：</label>
              <input id="feedback" v-model="mutualReviewData.reviewRecord.feedback" placeholder="输入反馈">
            </div>
            <div>
              <label for="score">作业得分：</label>
              <input id="score" v-model="mutualReviewData.reviewRecord.score" placeholder="输入得分">
            </div>
            <button @click="submitMutualReview(mutualReviewData.reviewRecord)">
              提交互改
            </button>
          </div>
          <div v-else>
            暂无互改作业
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from "axios";
import { useRoute } from "vue-router";

const activeTab = ref('activeCourses'); // 默认为在授课程
const showHomeworkList = ref(true); // 控制显示发布作业内容
const showHomeworkAnalysis = ref(false); // 控制显示作业分析内容
const showscore = ref(false); //  控制显示分数按钮

const route = useRoute();
const userData = route.query.userData ? JSON.parse(decodeURIComponent(route.query.userData as string)) : null;
console.log("@@@Studdent-CoursesSection", userData);

// 定义课程信息的类型
interface Course {
  id: number;
  code: string;
  name: string;
  uriLink: string;
  teacherCourse: {
    className: string;
    courseId: number;
    courseName: string;
  }
}

// 数据与状态
const courses = ref<Course[]>([]);
const showAddCourseModal = ref(false);
const courseCode = ref('');
const selectedCourseId = ref<number | null>(null);
const homeworkList = ref<any[]>([]);
const showCourseModal = ref(false);
const showMutualReviewModal = ref(false);
const selectedFiles = ref<{ [key: number]: File }>({});
const mutualReviewData = ref<{ uriLink: string, feedback: string, score: string }>({ uriLink: '', feedback: '', score: '' });
const studentId = userData.data.studentDetails.studentId;

// 获取已添加课程的函数
const fetchCourses = async () => {
  try {
    const response = await axios.post('http://localhost:8080/student/getCourseListAndImage', null, {
      params: { studentId }
    });
    courses.value = response.data;
    console.log("!!!!!!", courses.value);
  } catch (error) {
    console.error('获取课程列表失败:', error);
    alert('获取课程列表失败');
  }
};

// 添加课程的函数
const addCourse = async () => {
  if (!courseCode.value.trim()) {
    alert('请输入加课码');
    return;
  }

  try {
    const response = await axios.post('http://localhost:8080/student/joinCourse', null, {
      params: {
        studentId: studentId,
        entryCode: courseCode.value.trim()
      }
    });
    courses.value.push(response.data);
    showAddCourseModal.value = false;
    courseCode.value = '';
    await fetchCourses()
  } catch (error) {
    console.error('添加课程失败:', error);
    alert('添加课程失败');
  }
};

// 显示课程详情的函数
const showCourseDetails = async (courseId: number) => {
  selectedCourseId.value = courseId;
  showCourseModal.value = true;

  try {
    const response = await axios.post('http://localhost:8080/teacher/getHomeworkList', null, {
      params: { courseId: courseId }
    });
    homeworkList.value = response.data;
    console.log("@@getHomeworkList", homeworkList.value);

  } catch (error) {
    console.error('获取作业列表失败:', error);
    alert('获取作业列表失败');
  }
};

// 文件选择处理函数
const onFileChange = (event: Event, taskId: number) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files[0]) {
    selectedFiles.value[taskId] = target.files[0];
  }
};

// 提交作业的函数
const submitHomework = async (taskId: number) => {
  const file = selectedFiles.value[taskId];
  if (!file) {
    alert('请先选择要提交的作业文件');
    return;
  }

  const formData = new FormData();
  formData.append('file', file);
  formData.append('studentId', studentId.toString());
  formData.append('taskId', taskId.toString());

  try {
    const response = await axios.post('http://localhost:8080/student/submitHomework', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    alert('作业提交成功');
    console.log('提交作业成功:', response.data);

    // 从作业列表中移除已提交的作业
    homeworkList.value = homeworkList.value.filter(homework => homework.assignmentsTasks.taskId !== taskId);
  } catch (error) {
    console.error('提交作业失败:', error);
    alert('提交作业失败');
  }
};

// 显示作业互改详情的函数
const showMutualReview = async (taskId: number) => {
  try {
    const response = await axios.post('http://localhost:8080/student/mutualReview', null, {
      params: {
        taskId: taskId,
        studentId: studentId
      }
    });
    mutualReviewData.value = response.data;
    showMutualReviewModal.value = true;
    console.log('显示互改详情:', mutualReviewData.value);
  } catch (error) {
    console.error('获取互改详情失败:', error);
    alert('获取互改详情失败');
  }
};

// 提交互改的函数
const submitMutualReview = async (reviewRecord: object) => {
  try {
    console.log("reviewRecord", reviewRecord);
    const reviewRecordList = reviewRecord

    // const response = await axios.post('http://localhost:8080/student/scoreToOthers', reviewRecords.value);
    const response = await axios.post('http://localhost:8080/student/scoreToOthers', reviewRecordList);
    alert('互改提交成功');
    console.log('互改提交成功:', response.data);
    showMutualReviewModal.value = true;
  } catch (error) {
    console.error('互改提交失败:', error);
    alert('互改提交失败');
  }
};

const scoreAnalysisDetails = ref([]);

const handleHomeworkAnalysis = async (courseId: number) => {
  showHomeworkList.value = false;
  showHomeworkAnalysis.value = true;
  showscore.value = false;

  try {
    const requestData = { 
      courseId ,
      studentId
    }; // 封装参数为对象

    // 
    const Response = await axios.post('http://localhost:8080/student/getHomeworkScore', requestData);
    scoreAnalysisDetails.value = Response.data
    console.log('###scoreAnalysisDetails:', scoreAnalysisDetails);
  } catch (error) {
    console.error('调用接口时发生错误:', error);
  }
};

const AllScore = ref([]);

//  学习资料
const showAllScore = async (courseId: number) => {
  showHomeworkList.value = false;
  showHomeworkAnalysis.value = false;
  showscore.value = true;

  try {
    // const requestData = { 
    //   courseId ,
    //   studentId
    // }; // 封装参数为对象

    // 
    const Response = await axios.post('http://localhost:8080/student/getMaterialsList', null, {
      params: {
        courseId: courseId
      }
    });
    AllScore.value = Response.data
    console.log('###getAllScore:', AllScore.value);
  } catch (error) {
    console.error('调用接口时发生错误:', error);
  }
}

// 组件挂载时获取已添加课程
onMounted(() => {
  fetchCourses();
});
</script>

<style scoped>
.button-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}
</style>

<style scoped>
.courses-section {
  width: 1200px;
  height: 613px;
  background-color: #ffffff;
  margin: 0 auto;
  padding: 27px 33px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.tab {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
}

.tab span {
  cursor: pointer;
}

.tab .active {
  color: #007bff;
}

.courses {
  display: flex;
  gap: 20px;
}

.course-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border: 1px dashed #ddd;
  padding: 20px;
  border-radius: 8px;
  width: 160px;
  height: 160px;
  text-align: center;
  cursor: pointer;
}

.course-item img {
  height: 100px;
  margin-bottom: 10px;
}

.course-item p {
  margin: 0;
}

.add-course {
  border: 1px dashed #ddd;
}

.add-course span {
  font-size: 30px;
  color: #ddd;
}

.tabs {
  display: flex;
  margin-bottom: 20px;
}

.tab {
  margin-right: 20px;
  cursor: pointer;
  /* color: #007bff; */
}

.tab.active {
  font-weight: bold;
}

.course-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.course-card {
  border: 1px solid #ddd;
  border-radius: 4px;
  width: 150px;
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
}

.course-card.add-course {
  cursor: pointer;
}

.course-image {
  width: 100px;
  height: 100px;
  margin-bottom: 10px;
}

.add-icon {
  font-size: 24px;
  margin-bottom: 10px;
}

.delete-button {
  margin-top: 10px;
  background-color: #ff4d4f;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  border-radius: 4px;
}

.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.08);
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 50%;
  border-radius: 8px;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.homework-list {
  list-style-type: none;
  padding: 0;
}

.homework-item {
  border-bottom: 1px solid #ddd;
  padding: 10px 0;
}

.homework-description {
  font-weight: bold;
}

.homework-details {
  display: flex;
  justify-content: space-between;
  margin-top: 5px;
  margin-bottom: 10px;
}

.homework-details a {
  color: #007bff;
  text-decoration: none;
}

.homework-details a:hover {
  text-decoration: underline;
}

.active {
  color: blue;
  font-weight: bold;
  text-decoration: underline
}
.homework-analysis {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  margin: 0 auto;
}

.homework-analysis h2 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.analysis-card {
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.analysis-card p {
  margin: 5px 0;
  color: #555;
}

.analysis-card p:first-child {
  font-weight: bold;
}
</style>