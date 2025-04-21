<template>
  <!-- 教师签到的主容器 -->
  <div class="teacher-sign-in">
    <!-- 使用el-card组件包裹内容 -->
    <el-card class="box-card">

      <!-- 头部模板插槽 -->
      <template v-slot:header>
        <div class="clearfix">
          <span class="header-title">发布签到</span>
        </div>
      </template>
      <!-- 引入签到表单组件 -->
      <SignInForm />

    </el-card>

    <el-card class="box-card result-card">
      <!-- 头部模板插槽 -->
      <template v-slot:header>
        <div class="clearfix">
          <span class="header-title">签到记录</span>
        </div>
      </template>
      <!-- 展示签到结果的表格 -->
      <el-table :data="signInResults" style="width: 100%">
        <el-table-column prop="username" label="学生姓名" width="180"></el-table-column>
        <el-table-column prop="signInRecord.signInTime" label="签到时间" width="180"></el-table-column>
        <el-table-column prop="signInRecord.status" label="状态" width="180"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import SignInForm from '../SignIn/SignInForm.vue';
import { viewSignInResults } from '../../api/signIn';

export default defineComponent({
  name: 'TeacherSignIn', // 组件名称
  components: {
    SignInForm, // 引入的子组件
  },
  setup() {
    const signInResults = ref([]); // 定义一个响应式变量用于存储签到结果

    // 异步函数，用于获取签到结果
    const fetchSignInResults = async () => {
      try {
        // 调用API获取签到结果
        const response = await viewSignInResults(9);
        // 将获取到的数据赋值给响应式变量
        signInResults.value = response.data;
      } catch (error) {
        console.error('获取签到结果失败:', error);
      }
    };

    // 组件挂载时调用fetchSignInResults函数
    onMounted(() => {
      // 假设taskId为1
      fetchSignInResults(6);
    });
    onMounted(() => {
      document.body.style.backgroundColor = "#F1F5F8";
    });
    return {
      signInResults, // 返回响应式变量以便模板中使用
    };
  },

});

</script>

<style scoped>
.teacher-sign-in {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.box-card {
  width: 600px;
  margin-bottom: 20px;
}

.result-card {
  width: 600px;
}

.header-title {
  font-size: 32px;
  font-weight: bold;
  text-align: center;
  width: 100%;
}

.el-table .el-table__empty-block {
  text-align: center;
}
</style>