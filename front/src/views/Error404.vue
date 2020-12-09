<template>
  <v-container fluid mt-10>
    <v-row justify="center">
      <img src="../assets/error404.png" width="600" @click="goToHome" />
    </v-row>
    <v-row justify="center" align="center">
      <v-col md="6">
        <v-card flat class="transparent">
          <p style="font-size: 32px; color: #b52225">
            La page que vous recherchez n'est pas disponible ou n'existe pas ! ¯\_(ツ)_/¯
          </p>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  methods: {
    // Renvoie vers l'accueil
    goToHome () {
      this.$router.push('/')
    }
  }
}
</script>
