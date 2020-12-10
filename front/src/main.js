import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { store } from './store'
import vuetify from './plugins/vuetify'
import AlertError from '@/components/AlertError.vue'

Vue.component('app-alert-error', AlertError)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  created () {
    const currentUser = JSON.parse(localStorage.getItem('user'))
    if (currentUser) {
      store.commit('setUser', currentUser)
    }
  },
  render: h => h(App)
}).$mount('#app')
