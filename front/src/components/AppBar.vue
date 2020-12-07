<template>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-btn text to="/">
        <span class="btn"> Accueil</span>
        </v-btn>
      </div>

      <v-spacer></v-spacer>

      <div class="d-flex align-center">
        <v-btn>
          <span class="btn">Se connecter</span>
        </v-btn>

        <v-btn>
          <span class="btn">Inscription</span>
        </v-btn>
      </div>
    </v-app-bar>
</template>

<script>
export default {
  name: 'AppBar'
}
</script>
