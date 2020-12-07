import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // Nom de l'utilisateur
    user: null
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  },
  /**
   *
   * GETTERS
   *
   */
  getters: {
    user (state) {
      return state.user
    }
  }
})
