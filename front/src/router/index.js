import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('@/views/Profile')
  },
  {
    path: '/game',
    name: 'GameView',
    component: () => import('@/views/GameView')
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/About')
  },
  {
    path: '*',
    name: 'Error404',
    component: () => import('@/views/Error404'),
    meta: {
      title: 'Error 404 - Page not found'
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('@/views/Signup')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title
  next(next)
})

export default router
