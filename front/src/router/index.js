import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      title: 'Monopolight'
    }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('@/views/Profile'),
    meta: {
      title: 'My profile'
    }
  },
  {
    path: '/EnterCode',
    name: 'EnterCode',
    component: () => import('@/views/EnterCode'),
    meta: {
      title: 'Entrer le code'
    }
  },
  {
    path: '/game',
    name: 'GameView',
    component: () => import('@/views/GameView'),
    meta: {
      title: 'Game view'
    }
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/About'),
    meta: {
      title: 'About us'
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login'),
    meta: {
      title: 'Login'
    }
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('@/views/Signup'),
    meta: {
      title: 'Sign up'
    }
  },
  {
    path: '/registrationsuccessful',
    name: 'InscriptionConfirmation',
    component: () => import('@/views/InscriptionConfirmation'),
    meta: {
      title: 'Registration successful'
    }
  },
  {
    path: '/contact',
    name: 'ContactUs',
    component: () => import('@/views/ContactUs'),
    meta: {
      title: 'Contact us'
    }
  },
  {
    path: '/welcome',
    name: 'Welcome',
    component: () => import('@/views/Welcome'),
    meta: {
      title: 'Bienvenue'
    }
  },
  {
    path: '*',
    name: 'Error404',
    component: () => import('@/views/Error404'),
    meta: {
      title: 'Error 404 - Page not found'
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior (to, from, savedPosition) {
    if (to.hash) {
      return {
        selector: to.hash
      }
    } else {
      return {
        x: 0,
        y: 0
      }
    }
  }
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title
  next(next)
})

export default router
