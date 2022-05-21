import VueRouter from 'vue-router'
import Vue from 'vue'
import Login from '../components/Login.vue'
import StudentMain from '../components/student/StudentMain'
import ExamPaper from '../components/student/ExamPaper'
import AllScore from '../components/student/AllScore'
import StudentScore from '../components/student/StudentScore'
import PaperDetail from '../components/student/PaperDetail'
import ExamAdd from '../components/teacher/ExamAdd'
import NavMenu from '../components/teacher/NavMenu'
import QuestionAdd from '../components/teacher/QuestionAdd'
import QuestionBank from '../components/teacher/QuestionBank'
import StudentAdd from '../components/teacher/StudentAdd'
import TeacherHeader from '../components/teacher/TeacherHeader'

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
{
  path:'/StudentScore',
  name:'StudentScore',
  component:StudentScore
},
{
  path:'/PaperDetail',
  name:'PaperDetail',
  component:PaperDetail
},
{
  path:'/ExamAdd',
  name:'ExamAdd',
  component:ExamAdd
},
{
  path:'/NavMenu',
  name:'NavMenu',
  component:NavMenu
},
{
  path:'/QuestionAdd',
  name:'QuestionAdd',
  component:QuestionAdd
},
{
  path:'/QuestionBank',
  name:'QuestionBank',
  component:QuestionBank
},
{
  path:'/StudentAdd',
  name:'StudentAdd',
  component:StudentAdd
},
{
  path:'/TeacherHeader',
  name:'TeacherHeader',
  component:TeacherHeader
},
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router