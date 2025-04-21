<template>
  <div class="bottom">
    <h2>在学课程</h2>
    <div class="courses">
      <div class="course" @click="handleCourseClick">
        <img src="../assets/course1.jpg" alt="书本">
        <p>java业务驱动开发</p>
      </div>
      <div class="add-course" @click="handleAddCourseClick">
        <button>添加课程</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BottomComponent',
  methods: {
    handleCourseClick() {
      alert('跳转到对应课程页面');
    },
    handleAddCourseClick() {
      alert('添加课程页面');
    }
  }
}
</script>

<style scoped>
.bottom {
  padding: 20px;
  background-color: #e0e0e0;
}

.courses {
  display: flex;
}

.course {
  margin-right: 20px;
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.course img {
  width: 100px;
  height: 100px;
}

.add-course {
  display: flex;
  align-items: center;
}

button {
  padding: 10px;
  cursor: pointer;
}
</style>