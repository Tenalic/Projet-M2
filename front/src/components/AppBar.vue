<template>
<div>
    <v-app-bar app color="primary" dark>
      <!-- PARTIE GAUCHE -->
      <div class="d-flex align-center">
        <!-- Accueil -->
        <v-btn color="primary" elevation = 0 to="/" >
        <span class="btn"> Accueil</span>
        </v-btn>
      </div>
      <!-- FIN PARTIE GAUCHE -->

      <v-spacer></v-spacer>

      <!-- PARTIE DROITE -->
      <div class="d-flex align-center">
        <!-- Entrer un code -->
        <v-btn color="primary"
        to="EnterCode"
        elevation = 0>
            <span class="btn">Entrer un code</span>
        </v-btn>

        <!-- UTILISATEUR CONNECTE -->
        <div v-if="isUserAuthenticated == true">
          <v-btn color="primary"
          elevation = 0>
            <span class="btn">{{ getUsername }}</span>
          </v-btn>
        </div>

        <!-- UTILISATEUR NON CONNECTE -->
        <div v-else>
          <!-- Se connecter -->
          <v-btn color = "primary"
          elevation = 0
          to="Login">
            <span class="btn">Se connecter</span>
          </v-btn>
          <!-- Inscription -->
          <v-btn color = "primary"
          elevation = 0
          to="Signup">
            <span class="btn">Inscription</span>
          </v-btn>
        </div>
      </div>
      <!-- FIN PARTIE DROITE -->
    </v-app-bar>
</div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'AppBar',
  computed: {
    ...mapGetters(['user']),
    // Retourne si l'utilisateur est connecté
    isUserAuthenticated () {
      return (
        this.$store.getters.user !== null &&
        this.$store.getters.user !== undefined
      )
    }
  }
}
</script>
