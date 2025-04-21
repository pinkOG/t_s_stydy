import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/Home.vue';
import Home1 from '../views/Home1.vue';
import LoginView from '../views/Login.vue';
import RegisterView from '../views/Register.vue'; 
import TeacherHome from '../views/TeacherHome.vue';
import StudentHome from '../views/StudentHome.vue';
import Profile from '../components/Student/profile.vue';
import Test from "../views/Test.vue";
import Test2 from "../views/Test2.vue";
import SignInForm from "../components/SignIn/SignInForm.vue";
import StudentSignIn from "../components/SignIn/StudentSignIn.vue";
import TeacherSignIn from "../components/SignIn/TeacherSignIn.vue";
import Upload from "../components/Student/Upload.vue";
import TeacherHome1 from "../views/TeacherHome1.vue";
import Teacherprofile from "../components/Teacher/Teacherprofile.vue";


const routes = [
  {
    path:'/', 
    redirect:'/home'
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  }
  ,
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  }
  ,{
    path: '/teacher', 
    name: 'TeacherHome',
    component: TeacherHome
    
    },

  { 
    path: '/student', 
    name: 'StudentHome',
    component: StudentHome
   },
   { 
     path: '/home1', 
     name: 'Home1',
     component: Home1
    //  props: true
    },
    { 
      path: '/profile', 
      name: 'Profile',
      component: Profile
     },
     { 
       path: '/test', 
       name: 'Test',
       component: Test
      },
      { 
        path: '/test2', 
        name: 'Test2',
        component: Test2
       },
       {
        path: '/teacher-signin',
        name: 'TeacherSignIn',
        component: TeacherSignIn
      },
      {
        path: '/signin-form',
        name: 'SignInForm',
        component: SignInForm
      },
      {
        path: '/student-signin',
        name: 'StudentSignIn',
        component: StudentSignIn
      },
      {
        path: '/upload',
        name: 'Upload',
        component: Upload
      },
      {
        path: '/teacher-home1',
        name: 'TeacherHome1',
        component: TeacherHome1
      },
      {
        path: '/teacher-profile',
        name: 'Teacherprofile',
        component: Teacherprofile
      }

      
      
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});


// 全局守卫：登录拦截 本地没有存token,请重新登录
router.beforeEach((to, from, next) => {
	// 判断有没有登录
  // 如果是to.path定义的，直接放行
  if(to.path === "/login") return next()
  if(to.path === "/register") return next()
  if(to.path === "/home") return next()

  const tokenStr = window.sessionStorage.getItem("token")
	if (!tokenStr) {
	  return  next('/login')
	}
  next()
});


export default router;
