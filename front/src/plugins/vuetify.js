import Vue from 'vue'
import Vuetify from 'vuetify/lib/framework'
import '@fortawesome/fontawesome-free/css/all.css'

Vue.use(Vuetify)

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: '#d75838',
        secondary: '#db653',
        success: '#e8904d',
        error: '#b52225',
        primary_light: '#db653d',
        primary_dark: '#d14232',
        primary_additional_light: '#e5d5d1',
        primary_additional_dark: '#363535',
        primary_shadow: '#b52225',
        highlight: '#e8904d',
        app_grey: '#EAEDED'
      }
    }
  }
})
