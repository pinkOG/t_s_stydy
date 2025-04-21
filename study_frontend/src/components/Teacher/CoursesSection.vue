<template>
  <section class="courses-section">
    <div>
      <div class="tabs">
        <span style="margin-right: 20px; cursor: pointer;" :class="{ active: activeTab === 'activeCourses' }"
          @click="activeTab = 'activeCourses'">在授课程</span>
        <span style="cursor: pointer;" :class="{ active: activeTab === 'archivedCourses' }"
          @click="activeTab = 'archivedCourses'">已归档课程</span>
      </div>
      <div v-if="activeTab === 'activeCourses'" class="course-container">
        <div class="course-card add-course" @click="showAddCourseModal = true">
          <div class="add-icon">+</div>
          <div>添加课程</div>
        </div>
        <div v-for="course in courses" :key="course.code" class="course-card"
          @click="showCourseDetails(course.teacherCourse.courseId)">
          <img :src="course.uriLink" alt="Course Image" class="course-image" @click="openCourseDetails(course)">
          <div v-if="course.teacherCourse">课程：{{ course.teacherCourse.courseName }}</div>
          <div v-if="course.teacherCourse">班级：{{ course.teacherCourse.className }}</div>
          <div v-if="course.teacherCourse">加课码：{{ course.teacherCourse.entryNumber }}</div>
        </div>
      </div>
      <div v-else class="course-container">
        <div>暂无已归档课程</div>
      </div>

      <!-- 添加课程 -->
      <div v-if="showAddCourseModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="showAddCourseModal = false">&times;</span>
          <h2>添加课程</h2>
          <input v-model="courseName" placeholder="请输入课程名" style="margin-bottom: 10px;"><br>
          <input v-model="className" placeholder="请输入班级" style="margin-bottom: 20px;"><br>
          <button class="custom-file-button" @click="triggerFileInput">选择图片</button>
          <input type="file" accept="image/*" ref="fileInput" @change="handleFileChange" class="file-input"
            style="display: none;" />
          <button @click="addCourse" style="margin-left: 30px;">确认添加</button>
        </div>
      </div>

      <!-- 弹出框：发布作业和作业列表 -->
      <div v-if="selectedCourse" class="modal">
        <div class="modal-content">
          <span class="close" @click="selectedCourse = null">&times;</span>
          <div class="button-group">
            <button @click="showPublishHomework = true; showHomeworkList = false; showHomeworkAnalysis = false;">发布作业</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button @click="showPublishHomework = false; showHomeworkList = true; showHomeworkAnalysis = false;">作业列表</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button @click="handleHomeworkAnalysis(selectedCourse.teacherCourse.courseId)">作业分析</button>
          </div>

          <div v-if="showPublishHomework">
            <h2>发布作业</h2>
            教师 ID：<input v-model="selectedCourse.teacherCourse.teacherId" placeholder="Teacher ID" disabled
              style="margin-bottom: 10px;"><br>
            课程 ID：<input v-model="selectedCourse.teacherCourse.courseId" placeholder="Course ID" disabled
              style="margin-bottom: 10px;"><br>
            开始时间：<input type="datetime-local" v-model="selectedCourse.startTime" style="margin-bottom: 10px;"><br>
            结束时间：<input type="datetime-local" v-model="selectedCourse.endTime" style="margin-bottom: 20px;"><br>
            <div>
              <textarea v-model="selectedCourse.description" placeholder="作业描述"
                style="margin-bottom: -30px; margin-left: -100px;"></textarea><br>
              <button style="margin-right: -200px; margin-bottom:;" class="custom-file-button"
                @click="triggerFileInput1">选择文件</button>
              <input type="file"
                accept="application/pdf, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                ref="ABC" @change="handleFileChange1" class="file-input" style="display: none;" />
            </div>
            <div style="margin-top: 20px;">
              <button @click="updateCourseDetails" style="margin-right: 10px;">确认</button>
              <button @click="selectedCourse = null">取消</button>
            </div>
          </div>

          <div v-if="showHomeworkList">
            <h2>作业列表</h2>
            <ul v-if="homeworkList.length">
              <li v-for="homework in homeworkList" :key="homework.assignmentsTasks.taskId">
                <div class="homework-details" style="flex-direction: column;">
                  作业描述：{{ homework.assignmentsTasks.description }}
                  <a :href="homework.uriLink" target="_blank" style="margin-left: 150px;">下载链接</a>
                  <button @click="setInteraction(homework.assignmentsTasks.taskId)"
                    style="margin-left: 150px;">发布互改</button>
                </div>
                <hr>
              </li>
            </ul>
            <div v-else>
              暂无作业
            </div>
          </div>

          <div v-if="showHomeworkAnalysis" class="homework-analysis">
            <h2>作业分析</h2>
            <div v-for="(detail, index) in scoreAnalysisDetails" :key="index" class="analysis-card">
              <p>学生ID：{{ detail.studentId }}</p>
              <p>最高成绩：{{ detail.highestScore }}</p>
              <p>最低成绩：{{ detail.lowestScore }}</p>
              <p>平均成绩：{{ detail.averageScore }}</p>
              <p>最终成绩：{{ detail.finalScore }}</p>
            </div>
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
const route = useRoute();
const userData = route.query.userData ? JSON.parse(decodeURIComponent(route.query.userData as string)) : null;
console.log("@@@CoursesSection1", userData);

const fileInput = ref<HTMLInputElement | null>(null);
const triggerFileInput = () => {
  fileInput.value?.click();
};


// 定义课程信息的类型
interface Course {
  teacherId: number;
  code: string;
  name: string;
  uriLink: string;
  courseName: string;
  className: string;
  teacherCourse: {
    courseName: string;
    className: string;
    teacherId: number;
    courseId: number;
    entryNumber: string;
  };
  description?: string;
  startTime?: string;
  endTime?: string;
}

// 定义作业信息的类型
interface Homework {
  assignmentsTasks: {
    taskId: number;
    description: string;
    startTime: string;
    endTime: string;
  };
  uriLink: string;
}

// 数据与状态
const courses = ref<Course[]>([]);
const showAddCourseModal = ref(false);
const courseName = ref('');
const className = ref('');
const teacherId = userData.data.teacherDetails.teacherId;
const file = ref<File | null>(null); // 使用 ref 来保存文件对象
const file1 = ref<File | null>(null); // 新的文件对象
const selectedCourse = ref<Course | null>(null); // 当前选中的课程
const homeworkList = ref<Homework[]>([]); // 作业列表
const showPublishHomework = ref(true); // 控制显示发布作业内容
const showHomeworkList = ref(false); // 控制显示作业列表
const showHomeworkAnalysis = ref(false); // 控制显示作业分析内容
const selectedCourseId = ref<number | null>(null);
const showCourseModal = ref(false);

// 获取已添加课程的函数
const fetchCourses = async () => {
  try {
    const response = await axios.post('http://localhost:8080/teacher/getCourseAndImage', null, {
      params: {
        teacherId,
      }
    });
    courses.value = response.data.map((course: any) => ({
      ...course,
      teacherCourse: course.teacherCourse || { courseName: '', className: '' }
    }));
    console.log("@@@CoursesSection2", courses.value);
  } catch (error) {
    console.error('获取课程列表失败:', error);
    alert('获取课程列表失败');
  }
};

// 添加课程的函数
const addCourse = async () => {
  if (!courseName.value.trim() || !className.value.trim()) {
    alert('请输入课程名和班级信息');
    return;
  }

  if (!file.value) {
    alert('请上传图片');
    return;
  }

  const formData = new FormData();
  formData.append('file', file.value); // 确保 file 是一个 File 对象
  formData.append('teacherId', teacherId.toString()); // 确保 teacherId 是一个有效的整数
  formData.append('courseName', courseName.value.trim());
  formData.append('className', className.value.trim());

  try {
    await axios.post('http://localhost:8080/teacher/addCourses', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
    showAddCourseModal.value = false;
    courseName.value = '';
    className.value = '';
    file.value = null; // 重置文件输入
    alert('课程添加成功');
    await fetchCourses(); // 重新获取课程列表
  } catch (error) {
    console.error('添加课程失败:', error);
    alert('添加课程失败');
  }
};

// 处理文件选择
const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const selectedFile = target.files?.[0];
  // 文件类型检查
  if (selectedFile && selectedFile.type.startsWith('image/')) {
    file.value = selectedFile;
    console.log('已选择图片:', file.value);
  } else {
    alert('请选择一个图片文件');
  }
};

// 处理文件选择
const handleFileChange1 = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const selectedFile = target.files?.[0];
  //  文件类型检查
  if (selectedFile && (selectedFile.type === 'application/pdf' || selectedFile.type === 'application/msword' || selectedFile.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document')) {
    file1.value = selectedFile;
    console.log('已选择文件:', file1.value);
  } else {
    alert('请选择一个有效的文件（PDF 或 Word 文档）');
  }
};

const ABC = ref<HTMLInputElement | null>(null);

const triggerFileInput1 = () => {
  ABC.value?.click();
};



// 打开课程详情弹出框
const openCourseDetails = (course: Course) => {
  selectedCourse.value = { ...course };
  console.log("!!!!!", selectedCourse.value);
  showCourseDetails(course.teacherCourse.courseId); // 获取作业列表
};

// 更新课程详情
const updateCourseDetails = async () => {
  if (!selectedCourse.value || !file1.value) {
    alert('请上传文件');
    return;
  }

  const { teacherCourse, startTime, endTime, description } = selectedCourse.value;
  const formData = new FormData();
  formData.append('teacherId', teacherCourse.teacherId.toString());
  formData.append('courseId', teacherCourse.courseId.toString());
  formData.append('startTime', startTime || '');
  formData.append('endTime', endTime || '');
  formData.append('description', description || '');
  formData.append('file1', file1.value); // 添加文件

  console.log("formData:", formData);

  try {
    await axios.post('http://localhost:8080/teacher/publishHomeworkAndSave', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
    selectedCourse.value = null;
    file1.value = null; // 重置文件输入
    // await fetchCourses(); // 重新获取课程列表
    alert('作业发布成功');
  } catch (error) {
    console.error('更新课程详情失败:', error);
    alert('更新课程详情失败');
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

// 设置发布互改
const setInteraction = async (taskId: number) => {
  try {
    await axios.post('http://localhost:8080/teacher/setReviewAssignment', null, {
      params: {
        taskId: taskId,
      }
    });
    alert('发布互改成功');
  } catch (error) {
    console.error('发布互改失败:', error);
    alert('发布互改失败');
  }
};

const scoreAnalysisDetails = ref([]);

const handleHomeworkAnalysis = async (courseId: number) => {
  showPublishHomework.value = false;
  showHomeworkList.value = false;
  showHomeworkAnalysis.value = true;

  try {
    const requestData = { courseId }; // 封装参数为对象

    // 调用第一个接口
    const startResponse = await axios.post('http://localhost:8080/teacher/startScoreAnalysisInitial', requestData);
    if (startResponse.data) {
      // 如果第一个接口成功，调用第二个接口
      const detailResponse = await axios.post('http://localhost:8080/teacher/getScoreAnalysisInitialDetail', requestData);
      if (detailResponse.data) {
        scoreAnalysisDetails.value = detailResponse.data;
        console.log("scoreAnalysisDetails", scoreAnalysisDetails.value);
      } else {
        console.error('获取评分分析详情失败');
      }
    } else {
      console.error('启动评分分析初始化失败');
    }
  } catch (error) {
    console.error('调用接口时发生错误:', error);
  }
};

// 组件挂载时获取已添加课程
onMounted(() => {
  fetchCourses();
  if (ABC.value) {
    // 确认ABC.value是一个有效的DOM元素
    console.log("(ABC.value",ABC.value);
  }
});
</script>
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
  color: #007bff;
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
  width: 80%;
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
  max-width: 800px;
  margin: 0 auto;
}

.homework-analysis h2 {
  text-align: center;
  margin-bottom: 20px;
}

.analysis-card {
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.analysis-card p {
  margin: 8px 0;
}
</style>