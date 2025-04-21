<template>
  <div class="container">
    <el-card class="sign-in-card">
      <h1>学生签到系统</h1>
      <el-form @submit.prevent="handleSignIn" class="sign-in-form">
        <el-form-item label="签到码">
          <el-input v-model.number="form.signNumber" type="number" placeholder="请输入签到码" />
        </el-form-item>
        <el-form-item label="学生ID">
          <el-input v-model.number="form.studentId" type="number" placeholder="请输入学生ID" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSignIn">签到</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="sign-in-status-card">
      <h2>签到记录</h2>
      <el-button type="primary" @click="fetchSignInStatus">获取签到记录</el-button>
      <el-table :data="signInRecords" style="width: 100%; margin-top: 20px;">
        <el-table-column prop="username" label="学生" />
        <el-table-column prop="signTime" label="签到时间" />
        <el-table-column prop="signStatus" label="签到状态" />
      </el-table>
    </el-card>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref,onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import { signIn, fetchSignInStatusApi } from '../../api/signIn.ts'; // 导入API函数

interface SignInRecord {
  username: string;
  signTime: string;
  signStatus: string;
}

interface FormState {
  signNumber: number;
  studentId: number;
}

export default defineComponent({
  name: 'StudentSignIn',
  setup() {
    const form = reactive<FormState>({
      signNumber: 0,
      studentId: 0
    });

    const signInRecords = ref<SignInRecord[]>([]);

    const handleSignIn = async (): Promise<void> => {
      try {
        const data = (await signIn(form.signNumber, form.studentId)).data;
        if (data) {
          console.log(data);
          ElMessage.success('签到成功');
        } else {
          ElMessage.error('签到码错误或重复签到');
        }
      } catch (error: any) {
        console.error(error.message);
        ElMessage.error(error.message);
      }
    };

    const fetchSignInStatus = async (): Promise<void> => {
      try {
        const response = await fetchSignInStatusApi(form.studentId);
        const data = response.data;

        console.log('Fetched data:', data); // 添加调试信息

        // 确保 data 是一个数组
        if (Array.isArray(data)) {
          const formattedRecords = data.map((item: any) => ({
            username: item.username,
            signTime: item.signInRecord.signInTime,
            signStatus: item.signInRecord.status,
          }));
          signInRecords.value.splice(0, signInRecords.value.length, ...formattedRecords);
        } else {
          throw new Error('获取的数据不是数组');
        }
      } catch (error: any) {
        console.error(error.message);
        ElMessage.error('获取签到记录失败: ' + error.message);
      }
    };
    onMounted(() => {
      document.body.style.backgroundColor = "#F1F5F8";
    });
    return {
      form,
      signInRecords,
      handleSignIn,
      fetchSignInStatus,
    };
  },
});
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.sign-in-card,
.sign-in-status-card {
  margin-bottom: 20px;
}
</style>