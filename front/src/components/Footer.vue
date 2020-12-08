<template>
  <v-footer id="footer" padless>
    <v-row no-gutters>
      <v-col>
        <v-layout class="primary py-3" justify-center wrap>
          <v-btn v-for="(item, index) in footerLinks" :key="index" :to="item.link" color="white" large text tile>{{ item.title }}</v-btn>
        </v-layout>
      <v-divider class="primary darken-3"></v-divider>
      <v-layout class="primary py-2" justify-center wrap>
        <v-btn v-for="(social, index) in socialLinks" :key="index" href="#" dark class="mx-3" icon>
          <v-icon size="24px">{{ social.icon }}</v-icon>
        </v-btn>
      </v-layout>
      <v-layout justify-center grey darken-4 py-2 white--text>
        <p> &copy; {{ new Date().getFullYear() }} — <strong>Monopolight.com</strong> </p>
      </v-layout>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  data () {
    return {
      footerLinks: [
        { title: 'Home', link: '/' },
        { title: 'Contact us', link: '/contact' },
        { title: 'About us', link: '/about' },
        { icon: 'info', title: 'Sign in', link: '/sign-in' },
        { icon: 'info', title: 'Sign up', link: '/sign-up' }
      ],
      socialLinks: [
        { icon: 'mdi-facebook', link: 'https://www.facebook.com/' },
        { icon: 'mdi-whatsapp', link: '#' },
        { icon: 'mdi-youtube', link: 'https://youtube.com/' },
        { icon: 'mdi-instagram', link: 'https://www.instagram.com/' },
        { icon: 'mdi-twitter', link: 'https://twitter.com/' }
      ]
    }
  }
}
</script>

<style lang="css" scoped>
  #footer {
    bottom: 0;
  }
</style>
