<template>
  <div id="app">
    <Header :to="{
      userData
      //response
    }" />
    <main class="spaced">
      <UserSection />
      <CoursesSection :to="{
      userData
      //response
    }"/>
    </main>
  </div>
</template>

<script lang="ts" setup name="TeacherHome1">
import Header from '../components/Teacher/Header.vue';
import UserSection from '../components/Teacher/UserSection.vue';
import CoursesSection from '../components/Teacher/CoursesSection.vue'

// import { defineProps } from "vue";
import { useRoute } from "vue-router";

// const props = defineProps(['userData'])
// console.log(props);
const route = useRoute();
//  Home1拿到这个接口的内容，传到子组件Header中
const userData = route.query.userData ? JSON.parse(decodeURIComponent(route.query.userData as string)) : null;
// const response = route.query.response ? JSON.parse(decodeURIComponent(route.query.response as string)) : null;
console.log("@@@TeacherHome1", userData);

</script>

<style scoped>
.spaced {
  margin-bottom: 150px;
}

.container .spaced:last-child {
  margin-bottom: 0;
  /* 去除最后一个组件的底部间距 */
}
</style>