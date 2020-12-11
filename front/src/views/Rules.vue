<template>
  <v-container fluid mt-5>
    <v-row justify="center" align="center" no-gutters>
      <img src="../assets/logotnsi.png" height="300" width="auto" @click="goToHome" />
    </v-row>
    <v-row justify="center" align="center" no-gutters>
      <v-col md="6">
        <v-card flat color="mainBackground transparent">
            <h1 style="text-decoration:underline; color: #b52225"> Règles du jeu </h1>
             <li style="font-size: 24px; color: #b52225"> Acheter 2 cases de la même couleur pour obtenir un prix </li>
             <li style="font-size: 24px; color: #b52225"> Faire des achats pour obtenir des crédits ou des codes utilisables sur le site ou l'application mobile </li>
             <li style="font-size: 24px; color: #b52225"> Entrer les codes pour obtenir des crédits, des lancers de dés ou des prix </li>
             <br/> <br/>
          <p style="font-size: 24px; color: #b52225">
            Amusez-vous bien !
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
