<template>
  <div class="assignment-container">
    <div class="assignment-header">
      <div class="assignment-info">
        <h2>实验报告汇总提交</h2>
        <div class="assignment-details">
          <span>个人作业</span>
          <!-- <span>提交起止时间：24/05/29 21:11~24/06/09 23:00</span> -->
          <!-- <span>100分</span> -->
          <a href="#" class="view-submissions">查看谁交了</a>
        </div>
      </div>
      <button class="btn btn-primary">重新提交</button>
    </div>

    <div class="submission-content">
      <h3>提交内容</h3>
      <div class="teacher-comments">
        <h4>老师评语</h4>
        <p>暂无</p>
      </div>
      <div class="assignment-files">
        <h4>作业附件</h4>
        <div v-if="uploadedFile">
          <div class="file-item">
            <span>{{ uploadedFile.name }}</span>
            <span>{{ (uploadedFile.size / (1024 * 1024)).toFixed(2) }}MB</span>
            <a :href="uploadedFile.url" download class="btn btn-secondary">下载</a>
            <button @click="cancelDownload" class="btn btn-danger">取消下载</button>
          </div>
        </div>
        <div v-else>
          <input type="file" @change="handleFileChange" class="file-input" />
          <button @click="uploadAssignment" class="btn btn-primary">提交作业</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" name="Upload">
import { ref } from 'vue';
import axios from 'axios';

// 定义文件信息的类型
interface UploadedFile {
  name: string;
  size: number;
  url: string;
}

// 状态
const selectedFile = ref<File | null>(null);
const uploadedFile = ref<UploadedFile | null>(null);

// 处理文件选择
const handleFileChange = (event: Event) => {
  const input = event.target as HTMLInputElement;
  if (input.files && input.files[0]) {
    selectedFile.value = input.files[0];
  }
};

// 提交作业
const uploadAssignment = async () => {
  if (!selectedFile.value) {
    alert('请选择一个文件');
    return;
  }

  const formData = new FormData();
  formData.append('file', selectedFile.value);

  try {
    const response = await axios.post('/src/api/upload.js', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    uploadedFile.value = {
      name: selectedFile.value.name,
      size: selectedFile.value.size,
      url: response.data.url
    };

    selectedFile.value = null;
  } catch (error) {
    console.error('上传作业失败:', error);
    alert('上传作业失败');
  }
};

// 取消下载
const cancelDownload = () => {
  uploadedFile.value = null;
};
</script>

<style scoped>
.assignment-container {
  padding: 20px;
  background: #f5f5f5;
  border-radius: 8px;
}

.assignment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.assignment-info {
  max-width: 70%;
}

.assignment-details span {
  display: inline-block;
  margin-right: 15px;
}

.view-submissions {
  color: #007bff;
  text-decoration: none;
}

.submission-content {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
}

.teacher-comments,
.assignment-files {
  margin-bottom: 20px;
}

.file-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.file-input {
  display: block;
  margin-bottom: 10px;
}

.btn {
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn-primary {
  background: #007bff;
  color: #fff;
}

.btn-secondary {
  background: #6c757d;
  color: #fff;
}

.btn-danger {
  background: #dc3545;
  color: #fff;
}
</style>