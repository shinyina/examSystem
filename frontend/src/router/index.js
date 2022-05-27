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
import StudentLook from '../components/teacher/StudentLook';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Login'
},
{
  path:'/Login',
  name:'Login',
  meta:{authType:0},
  component:Login
},
{
  path:'/StudentMain',
  name:'StudentMain',
  meta:{authType:1},
  component:StudentMain
},
{
  path:'/ExamPaper',
  name:'ExamPaper',
  meta:{authType:1},
  component:ExamPaper
},
{
  path:'/AllScore',
  name:'AllScore',
  meta:{authType:1},
  component:AllScore
},
{
  path:'/StudentScore',
  name:'StudentScore',
  meta:{authType:1},
  component:StudentScore
},
{
  path:'/PaperDetail',
  name:'PaperDetail',
  meta:{authType:1},
  component:PaperDetail
},
{
  path:'/ExamAdd',
  name:'ExamAdd',
  meta:{authType:2},
  component:ExamAdd
},
{
  path:'/NavMenu',
  name:'NavMenu',
  meta:{authType:2},
  component:NavMenu
},
{
  path:'/QuestionAdd',
  name:'QuestionAdd',
  meta:{authType:2},
  component:QuestionAdd
},
{
  path:'/QuestionBank',
  name:'QuestionBank',
  meta:{authType:2},
  component:QuestionBank
},
{
  path:'/StudentAdd',
  name:'StudentAdd',
  meta:{authType:2},
  component:StudentAdd
},
{
  path:'/TeacherHeader',
  name:'TeacherHeader',
  meta:{authType:2},
  component:TeacherHeader
},
{
  path:'/StudentLook',
  name:'StudentLook',
  meta:{authType:2},
  component:StudentLook
},
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {
  if(to.meta.authType===2){
    if(sessionStorage.getItem('nickname')=='teacher'){
      next()
    }
    else{
      next({
        path:'/Login',
        query:{error:1}
      })
      location.reload()
    }
  }
  else if(to.meta.authType===1){
    if(sessionStorage.getItem('uid'))
    next()
    else{
      next({
        path:'/Login',
        query:{error:2}
      })
      location.reload()
    }
  }
  else if(to.meta.authType===0)
  next()
})
export default router