import Vue from 'vue'
import Vuetify from 'vuetify/lib/framework'

Vue.use(Vuetify)

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: '#d75838',
        secondary: '#db653',
        success: '#e8904d',
        error: '#b52225'
      }
    }
  }
})
