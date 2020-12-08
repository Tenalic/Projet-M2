<template>
  <v-footer id="footer" padless>
    <v-row no-gutters class="grey darken-4">
      <v-col cols="12" class="primary text-center py-2">
            <v-btn v-for="(item, index) in footerLinks" :key="index" :to="item.link" color="white" large text tile>{{ item.title }}</v-btn>
      </v-col>
      <v-col cols="12" class="primary text-center py-2 mt-1">
        <v-btn v-for="(social, index) in socialLinks" :key="index" href="#" dark class="mx-3" icon>
          <v-icon size="24px">{{ social.icon }}</v-icon>
        </v-btn>
      </v-col>
      <v-col cols="12" class="grey darken-4 text-center white--text pt-3">
        <p color="white"> &copy; {{ new Date().getFullYear() }} — <strong>Monopolight.com</strong> </p>
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
