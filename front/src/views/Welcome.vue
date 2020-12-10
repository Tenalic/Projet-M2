<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="12" md="8" offset-md="2" xl="6" offset-xl="3" align="center">
        <v-card class="mb-2">
          <v-progress-linear v-model="value" :buffer-value="bufferValue"></v-progress-linear>
          <v-card-text class="pb-1">
            <p>Vous serez redirigé vers le <router-link id="home-link" to="/">la page d'Accueil</router-link> dans {{timeLeft}} secondes </p>
          </v-card-text>
        </v-card>
        <v-card class="px-5 pb-5">
          <v-card-title class="justify-center">
            <p class="farmer_green--text headline mb-0">Bienvenue sur Monopolight</p>
          </v-card-title>
          <p class="welcome pt-3">Merci de nous rejoindre!</p>
          <v-card-actions class="justify-center">
            <v-btn outlined color="primary" @click="goToPath('/')">Commencer l'aventure</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import router from '@/router'

export default {
  data () {
    return {
      value: 0,
      bufferValue: 100,
      timeLeft: 15
    }
  },
  methods: {
    startBuffer () {
      this.interval = setInterval(() => {
        this.value += this.bufferValue / 20
        this.timeLeft--
        if (this.timeLeft <= 0) {
          router.push('/')
        }
      }, 1000)
    }
  },
  mounted () {
    this.startBuffer()
  },
  beforeDestroy () {
    clearInterval(this.interval)
  }
}
</script>

<style lang="css" scoped>
* {
  font-family: 'Muli', sans-serif;
}
.welcome {
  font-size: 20px;
}

#home-link {
  text-decoration: none;
}
</style>
