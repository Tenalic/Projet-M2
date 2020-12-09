<template>
  <v-container fluid mt-5>
    <v-row justify="center" align="center">
      <img src="../assets/logotnsi.png" width="400" @click="goToHome" />
    </v-row>
    <v-row justify="center" align="center">
      <v-col md="6">
        <v-card flat color="mainBackground transparent">
          <p style="font-size: 24px; color: #b52225">
            Monopolight est un projet réalisé par les étudiants de seconde année
            de Master TNSI FI à l'Université Polytechnique des Hauts-de-France. Entrez vos
            codes sur ce site web, jouez, et remportez des prix ! Egalement disponible sur
            mobile ! Amusez vous bien !
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
