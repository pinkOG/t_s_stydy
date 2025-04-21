<template>
  <section class="user-section">
    <div class="user-card">
      <img src="../../assets/teacher/teacher.png" alt="user-avatar" />
      <p><span style="font-size:25px;">老师</span>，下午好！厦门理工学院</p>
      <div class="progress-bar">
        <span style="font-size:15px">想访问学生端？点击访问 <a href="http://localhost:5173/login">学生端</a></span>
      </div>
    </div>
    <div class="notifications">
      <div class="notification-item" @click="setSignIn">
        <img src="../../assets/student/签到 (1).png" alt="设定签到" />
        <span>设定签到</span>
      </div>
      <div class="notification-item">
        <img src="../../assets/student/作业.png" alt="发布作业" />
        <span>发布作业</span>
      </div>
      <div class="notification-item" @click="showUploadModal">
        <img src="../../assets/teacher/发布视频.png" alt="发布视频、课件" />
        <span>发布视频、课件</span>
      </div>
      <div class="notification-item" @click="showAddCourseModal = false">
        <img src="../../assets/teacher/发布.png" alt="发布作业互改" />
        <span>发布作业互改</span>
      </div>
    </div>

    <!-- 发布视频和课件的弹出框 -->
    <div v-if="isUploadModalVisible" class="modal">
      <div class="modal-content">
        <span class="close" @click="hideUploadModal">&times;</span>
        <h2>上传视频或课件</h2>
        <form @submit.prevent="submitUpload">
          <div style="margin-bottom: 10px;">
            <label for="uploadTitle">标题内容:</label>
            <input type="text" id="uploadTitle" v-model="uploadTitle" required />
          </div>
          <div style="margin-bottom: 10px;">
            <label for="courseId">课程ID:</label>
            <input type="text" id="courseId" v-model="courseId" required />
          </div>
          <div style="margin-bottom: 25px;">
            <label for="uploadDescription">描述详情:</label>
            <textarea id="uploadDescription" v-model="uploadDescription" required></textarea>
          </div>
          <div style="margin-bottom: 20px;margin-left: 78px;">
            <label for="uploadFile">上传文件:</label>
            <input type="file" id="uploadFile" @change="handleUploadFile" />
          </div>
          <div class="button-group">
            <button type="submit">提交</button>
          </div>
        </form>
      </div>
    </div>

    <!-- 发布作业互改弹出框 -->
    <div v-if="showAddCourseModal" class="modal">
      <div class="modal-content" >
        <span class="close" @click="showAddCourseModal = false">&times;</span>
        <h2>确认发布作业互改吗？</h2>
        <button @click="publishHomework">确认发布</button>
      </div>
    </div>


  </section>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import axios from 'axios';
// import router from "../../router";
import { useRoute, useRouter } from "vue-router";


// 控制上传视频或课件弹出框的显示和隐藏
const isUploadModalVisible = ref(false);
const uploadTitle = ref('');
const uploadDescription = ref('');
const courseId = ref('');  // 新增的 courseId 变量
const uploadFile = ref<File | null>(null);
const showAddCourseModal = ref(false);
// const homeworkList = ref<any[]>([]);

const router = useRouter();
const route = useRoute();
const userData = route.query.userData ? JSON.parse(decodeURIComponent(route.query.userData as string)) : null;
console.log("@@@UserSection", userData);

const teacherId = userData.data.teacherDetails.teacherId;
console.log("UserSection:teacherId:",teacherId);
const courses = ref<Course[]>([]);

interface Course {
  id: number;
  code: string;
  name: string;
  uriLink: string;
  teacherCourse: {
    className: string;
    courseId: number;
    courseName:string;
  }
}

const showUploadModal = () => {
  isUploadModalVisible.value = true;
};

const hideUploadModal = () => {
  isUploadModalVisible.value = false;
};



function setSignIn() {
  router.push({
    name: 'TeacherSignIn'
  });
}

// 处理文件上传
const handleUploadFile = (event: Event) => {
  const target = event.target as HTMLInputElement;
  uploadFile.value = target.files ? target.files[0] : null;
};

// 上传视频、课件
const submitUpload = async () => {
  if (!uploadTitle.value.trim() || !uploadDescription.value.trim() || !courseId.value.trim()) {
    alert('请输入标题、描述和课程ID');
    return;
  }

  const formData = new FormData();
  formData.append('title', uploadTitle.value.trim());
  formData.append('description', uploadDescription.value.trim());
  formData.append('courseId', courseId.value.trim());  // 添加 courseId
  if (uploadFile.value) {
    formData.append('files', uploadFile.value);
  }

  try {
    const response = await axios.post('http://localhost:8080/files/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    console.log("@@Teacherupload", response);
    hideUploadModal();
    alert('视频、课件上传成功');
  } catch (error) {
    console.error('上传失败:', error);
    alert('上传失败');
  }
};

// 发布作业互改的函数
const publishHomework = async () => {
  try {
    const response = await axios.post('http://localhost:8080/teacher/getCourseListAndTaskId', null, {
      params: {
        teacherId: teacherId
      }
    });
    courses.value.push(response.data);
    console.log("getCourseListAndTaskId",response);
    alert('发布作业互改成功');
    showAddCourseModal.value = false;
    // courseCode.value = '';
    // await fetchCourses()
  } catch (error) {
    console.error('互改任务发布失败:', error);
    alert('互改任务发布失败');
  }
};




onMounted(() => {
  document.body.style.backgroundColor = "#F1F5F8";
});
</script>

<style scoped>
.user-section {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 1266px;
  height: 182px;
  margin: 0 auto;
  margin-top: 20px;
  margin-bottom: -15px;
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
  font-size: 30px;
  margin-left: 0px;
  margin-top: 80px
}

.progress-bar span {
  margin-right: 5px;
  font-size: 12px;
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

.modal {
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

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  position: relative;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  cursor: pointer;
}

.button-group {
  width: 100px;
  display: flex;
  justify-content: space-between;
  margin: 10px auto;
}

.button-group button {
  flex: 1;
  margin: 0 5px;
}
</style>