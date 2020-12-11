import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    // Données de l'utilisateur
    user: null,
    error: null,
    API_URL: 'https://monopolym2tnsi.hopto.org:8443'
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

      // add user to localStorage
      window.localStorage.setItem('user', JSON.stringify(state.user))
    },
    clearUser (state) {
      state.user = null
      // delete user data from localStorage
      window.localStorage.clear()
      router.push('/')
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
            resolve()
          })
          .catch((err) => {
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
            if (response.data.message) {
              reject(response.data)
            } else {
              commit('setUser', response.data)
              resolve()
            }
          })
      })
    },
    onContactUs ({ commit }, payload) {
      commit('clearError')
      return new Promise((resolve, reject) => {
        console.log('payload: ', payload)
        resolve()
      })
    },
    updateUserProfile ({ commit, getters }, payload) {
      commit('clearError')
      return new Promise((resolve, reject) => {
        const url = getters.API_URL
        console.log(payload)
        axios({
          method: 'put',
          url: `${url}/account/update`,
          data: payload,
          headers: {
            'Access-Control-Allow-Origin': '*',
            IdAccount: this.account.id
          }
        })
          .then((response) => {
            // commit('setUser', response.data)
            console.log(response.data)
            resolve()
          })
          .catch((err) => {
            console.log(err)
            reject(err)
          })
      })
    },
    // Met à jour l'user dans le state
    updateUser ({ commit }, payload) {
      commit('setUser', payload)
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
