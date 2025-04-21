<template>
  <section class="courses-section">
    <div>
      <div class="tabs">
        <span class="tab active">在授课程</span>
        <span class="tab">已归档课程</span>
      </div>
      <div class="course-container">
        <div class="course-card add-course" @click="showAddCourseModal = true">
          <div class="add-icon">+</div>
          <div>添加课程</div>
        </div>
        <div v-for="course in courses" :key="course.code" class="course-card">
          <img :src="course.uriLink" alt="Course Image" class="course-image">
          <div v-if="course.teacherCourse">{{ course.teacherCourse.courseName }}</div>
          <div v-if="course.teacherCourse">{{ course.teacherCourse.className }}</div>
        </div>
      </div>
      
      <div class="upload-homework">
        <!-- <button @click="showUploadHomeworkModal = true" class="upload-button">作业上传</button> -->
      </div>

      <div v-if="showAddCourseModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="showAddCourseModal = false">&times;</span>
          <h2>添加课程</h2>
          <input v-model="courseName" placeholder="请输入课程名" style="margin-bottom: 10px;"><br>
          <input v-model="className" placeholder="请输入班级" style="margin-bottom: 20px;"><br>
          <button class="custom-file-button" @click="triggerFileInput">选择图片</button>
          <input
            type="file"
            accept="image/*"
            ref="fileInput"
            @change="handleFileChange"
            class="file-input"
            style="display: none;"
          />
          <button @click="addCourse" style="margin-left: 30px;">确认添加</button>
        </div>
      </div>

      <!-- 上传作业的弹出框 -->
      <div v-if="showUploadHomeworkModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="showUploadHomeworkModal = false">&times;</span>
          <h2>上传作业</h2>
          <input type="file" accept="application/pdf" @change="handleHomeworkFileChange" style="margin-bottom: 20px;"><br>
          <button @click="uploadHomework" style="margin-left: 30px;">确认上传</button>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from "axios";
import { useRoute } from "vue-router";

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
  };
}

// 数据与状态
const courses = ref<Course[]>([]);
const showAddCourseModal = ref(false);
const showUploadHomeworkModal = ref(false); // 控制作业上传弹出框的显示状态
const courseName = ref('');
const className = ref('');
const teacherId = userData.data.teacherDetails.teacherId;
const file = ref<File | null>(null); // 使用 ref 来保存文件对象
const homeworkFile = ref<File | null>(null); // 保存上传的作业文件

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
    console.log("@@@CoursesSection2", response);
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
    const response = await axios.post('http://localhost:8080/teacher/addCourses', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
    courses.value.push(response.data);
    showAddCourseModal.value = false;
    courseName.value = '';
    className.value = '';
    file.value = null; // 重置文件输入
  } catch (error) {
    console.error('添加课程失败:', error);
    alert('添加课程失败');
  }
};

// 处理文件选择
const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const selectedFile = target.files?.[0];
  if (selectedFile && selectedFile.type.startsWith('uriLink/')) {
    file.value = selectedFile;
    console.log('已选择图片:', file.value);
  } else {
    alert('请选择一个图片文件');
  }
};

// 处理作业文件选择
const handleHomeworkFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const selectedFile = target.files?.[0];
  if (selectedFile && selectedFile.type === 'application/pdf') {
    homeworkFile.value = selectedFile;
    console.log('已选择作业文件:', homeworkFile.value);
  } else {
    alert('请选择一个 PDF 文件');
  }
};

// 上传作业的函数
const uploadHomework = async () => {
  if (!homeworkFile.value) {
    alert('请选择作业文件');
    return;
  }

  const formData = new FormData();
  formData.append('file', homeworkFile.value);
  formData.append('teacherId', teacherId.toString());

  try {
    const response = await axios.post('http://localhost:8080/teacher/publishHomeworkAndSave', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
    console.log('作业上传成功:', response.data);
    showUploadHomeworkModal.value = false;
    homeworkFile.value = null; // 重置文件输入
  } catch (error) {
    console.error('上传作业失败:', error);
    alert('上传作业失败');
  }
};

// 组件挂载时获取已添加课程
onMounted(() => {
  fetchCourses();
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
  background-color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0.4);
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
</style>