import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    // Nom de l'utilisateur
    user: null,
    error: null,
    API_URL: 'http://monopolym2tnsi.hopto.org:8080'
  },
  mutations: {
    setError (state, payload) {
      state.error = payload
    },
    clearError (state) {
      state.error = null
    },
    setUser (state, payload) {
      state.user = payload
    }
  },
  actions: {
    signUserIn ({ commit, getters }, payload) {
      commit('clearError')

      return new Promise((resolve, reject) => {
        const url = getters.API_URL
        const pseudoPassword = payload.email + ':' + payload.password
        var basicAuth = `Basic ${btoa(pseudoPassword)}`

        axios({
          method: 'post',
          url: `${url}/account/connection`,
          data: {},
          headers: { Authorization: basicAuth }
        })
          .then((response) => {
            commit('setUser', response.data)
            console.log('user: ', response.data)
            resolve()
          })
          .catch((err) => {
            console.log('err: ', err)
            reject(err)
          })
      })
    },
    signUserUp ({ commit, getters }, payload) {
      commit('clearError')
      return new Promise((resolve, reject) => {
        const url = getters.API_URL
        axios({
          method: 'post',
          url: `${url}/account/create`,
          data: payload
        })
          .then((response) => {
            commit('setUser', response.data)
            resolve()
          })
          .catch((err) => {
            reject(err)
          })
      })
    },
    onContactUs ({ commit }, payload) {
      commit('clearError')
      return new Promise((resolve, reject) => {
        console.log('payload: ', payload)
        resolve()
      })
    }
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
    },
    error (state) {
      return state.error
    },
    API_URL (state) {
      return state.API_URL
    }
  }
})
