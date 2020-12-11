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
      title: 'Bienvenue sur Monopolight'
    }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('@/views/Profile'),
    meta: {
      title: 'Monopolight - Profil'
    }
  },
  {
    path: '/game',
    name: 'GameView',
    component: () => import('@/views/GameView'),
    meta: {
      title: 'Monopolight - Jeu'
    }
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/About'),
    meta: {
      title: 'Monopolight - À propos'
    }
  },
  {
    path: '/rules',
    name: 'Rules',
    component: () => import('@/views/Rules'),
    meta: {
      title: 'Monopolight - Règles'
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login'),
    meta: {
      title: 'Monopolight - Connexion'
    }
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('@/views/Signup'),
    meta: {
      title: 'Monopolight - Inscription'
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
      title: 'Monopolight - Nous contacter'
    }
  },
  {
    path: '/welcome',
    name: 'Welcome',
    component: () => import('@/views/Welcome'),
    meta: {
      title: 'Monopolight - Bienvenue'
    }
  },
  {
    path: '/enter-code',
    name: 'EnterCode',
    component: () => import('@/views/EnterCode'),
    meta: {
      title: 'Monopolight - Entrer un code'
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
