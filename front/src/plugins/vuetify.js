import Vue from 'vue'
import Vuetify from 'vuetify/lib/framework'

Vue.use(Vuetify)

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: '#d75838',
        primary_light: '#db653d',
        primary_dark: '#d14232'
      }
    }
  }
})
