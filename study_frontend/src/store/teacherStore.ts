// src/store/teacherStore.ts
import { defineStore } from 'pinia';

export const useTeacherStore = defineStore('teacher', {
  state: () => ({
    videos: [],
    assignments: [],
    // 其他状态
  }),
  actions: {
    uploadVideo(video: any) {
      this.videos.push(video);
    },
    uploadAssignment(assignment: any) {
      this.assignments.push(assignment);
    },
    // 其他操作
  },
});