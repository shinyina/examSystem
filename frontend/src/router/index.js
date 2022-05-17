import VueRouter from 'vue-router'
import Vue from 'vue'
import Login from '../components/Login.vue'
import StudentMain from '../components/student/StudentMain'
import ExamPaper from '../components/student/ExamPaper'
import AllScore from '../components/student/AllScore'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Login'
},
{
  path:'/Login',
  name:'Login',
  component:Login
},
{
  path:'/StudentMain',
  name:'StudentMain',
  component:StudentMain
},
{
  path:'/ExamPaper',
  name:'ExamPaper',
  component:ExamPaper
},
{
  path:'/AllScore',
  name:'AllScore',
  component:AllScore
},
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router