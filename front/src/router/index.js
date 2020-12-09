import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import GameView from '../views/GameView.vue'
import Error404 from '../views/Error404.vue'
import Login from '../views/Login.vue'
import Signup from '../views/Signup.vue'
import EnterCode from '../views/EnterCode.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
    // children: [
    // ]
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('@/views/Profile')
  },
  {
    path: '/enterCode',
    name: 'EnterCode',
    component: EnterCode
  },
  {
    path: '/game',
    name: 'GameView',
    component: GameView
  },
  {
    path: '/game',
    name: 'GameView',
    component: GameView
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '*',
    name: 'Error404',
    component: Error404,
    meta: {
      title: 'Error 404 - Page not found'
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
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
