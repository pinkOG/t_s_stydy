<template>
  <div>
    <button @click="triggerFileInput">选择文件</button>
    <input type="file" ref="fileInput" @change="handleFileChange" style="display: none" />
  </div>
</template>

<script lang="ts" setup >
import { ref } from 'vue';

// 引用文件输入元素
const fileInput = ref<HTMLInputElement | null>(null);

// 触发文件输入对话框
const triggerFileInput = () => {
  fileInput.value?.click();
};

// 处理文件变化
// const handleFileChange = (event: Event) => {
//   const target = event.target as HTMLInputElement;
//   const selectedFile = target.files?.[0];
//   if (selectedFile) {
//     console.log('已选择文件:', selectedFile);
//     // 你可以在这里添加更多的文件处理逻辑
//   }
// };
</script>
<style scoped>
button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}
</style>