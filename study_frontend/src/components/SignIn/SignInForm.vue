<template>
  <!-- 表单组件，绑定的数据模型是form -->
  <el-form :model="form" label-width="120px">
    <!-- 设置label的宽度 -->
    <!-- 表单项：教师ID -->
    <el-form-item label="教师ID">
      <!-- 输入框，绑定的数据模型是form.teacherId，类型是数字，并设置大小 -->
      <el-input v-model.number="form.teacherId" type="number" :style="{ width: '220px', height: '31.99px' }"></el-input>
    </el-form-item>

    <!-- 表单项：开始时间 -->
    <el-form-item label="开始时间">
      <!-- 日期时间选择器，绑定的数据模型是form.startTime，类型是datetime，并设置大小 -->
      <el-date-picker v-model="form.startTime" type="datetime" :style="{ width: '220px' }"></el-date-picker>
    </el-form-item>

    <!-- 表单项：结束时间 -->
    <el-form-item label="结束时间">
      <!-- 日期时间选择器，绑定的数据模型是form.endTime，类型是datetime，并设置大小 -->
      <el-date-picker v-model="form.endTime" type="datetime" :style="{ width: '220px' }"></el-date-picker>
    </el-form-item>

    <!-- 表单项：提交按钮 -->
    <el-form-item>
      <!-- 按钮，点击时触发handleSubmit方法，并设置大小 -->
      <el-button type="primary" @click="handleSubmit" :style="{ width: '176.02px', height: '30px' }">创建签到任务</el-button>
    </el-form-item>
  </el-form>

  <!-- 卡片组件显示创建任务成功信息 -->
  <el-card v-if="responseData.id" class="task-card">
    <template v-slot:header>
      <div class="clearfix">
        <span>签到码</span>
      </div>
    </template>
    <div class="task-card-content">
      <p class="task-id">{{ responseData.id }}</p>
    </div>
  </el-card>
</template>

<script lang="ts" >
import { defineComponent, reactive } from 'vue';  // 引入Vue的defineComponent、reactive和ref函数
import { ElMessage } from 'element-plus';  // 引入Element Plus的消息组件
import { createSignInTask } from '../../api/signIn.ts';  // 引入创建签到任务的API函数

export default defineComponent({
  name: 'SignInForm',  // 组件名称
  setup() {
    // 获取当前系统时间
    const currentTime = new Date();

    // 定义表单的数据模型，使用reactive使其响应式
    const form = reactive({
      teacherId: 0,       // 教师ID，初始值为0
      startTime: currentTime,      // 开始时间，初始为当前系统时间
      endTime: currentTime,        // 结束时间，初始为当前系统时间
    });

    // 定义响应数据的状态
    const responseData = reactive({
      id: '',
      teacherId: '',
      startTime: '',
      endTime: ''
    });

    // 提交表单的方法，使用async/await处理异步请求
    const handleSubmit = async () => {
      try {
        // 将日期类型转换为字符串类型
        const formattedStartTime = form.startTime.toISOString();
        const formattedEndTime = form.endTime.toISOString();

        // 调用创建签到任务的API函数，并传入表单数据
        const response = await createSignInTask(form.teacherId, formattedStartTime, formattedEndTime);

        // 如果成功，打印成功信息并显示弹窗
        console.log('创建成功:', response);
        // 设置响应数据
        responseData.id = response;
        ElMessage.success('创建成功，签到码：' + response);
      } catch (error) {
        // 如果失败，打印错误信息并显示错误消息
        console.error('创建失败:', error);
        ElMessage.error('创建失败，请重试');
      }
    };

    // 返回表单数据和提交方法，以便在模板中使用
    return {
      form,
      responseData,
      handleSubmit,
    };
  },
});
</script>

<style scoped>
/* 美化卡片组件 */
.task-card {
  margin-top: 20px;
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  background-color: #fff;
  text-align: center;
  /* 设置卡片内容居中对齐 */
}

/* 设置任务ID的字体大小和加粗 */
.task-card .task-id {
  font-size: 24px;
  font-weight: bold;
}
</style>