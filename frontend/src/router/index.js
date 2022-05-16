import VueRouter from 'vue-router'
import Vue from 'vue'
import Login from '../components/Login'

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
}
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router