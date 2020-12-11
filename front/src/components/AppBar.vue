<template>
<div>
    <v-app-bar app color="primary" dark>
      <!-- PARTIE GAUCHE -->
      <div class="d-flex align-center">
        <!-- Accueil -->
        <img src="../assets/logotnsi3.png" height="100" width="auto" class="mt-5">
      </div>
      <!-- FIN PARTIE GAUCHE -->

      <v-spacer></v-spacer>

      <!-- PARTIE DROITE -->
      <div class="d-flex align-center">
        <!-- Entrer un code -->
        <v-btn color="primary"
        to="/EnterCode"
        elevation = 0>
            <span class="btn">Entrer un code</span>
        </v-btn>

        <!-- UTILISATEUR CONNECTE -->
        <div v-if="isUserAuthenticated == true">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
              color="primary"
              elevation = 0
              v-bind="attrs"
              v-on="on"
              >
                <span class="btn">{{ user.firstname }}</span>
              </v-btn>
            </template>
            <v-list class="primary_light">
              <v-list-item v-for="item in items" :key="item.title" router :to="item.link">
                <v-list-item-title class="white--text"><v-icon class="white--text mr-2"> {{ item.icon }}</v-icon>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
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
            <span class="btn">S'inscrire</span>
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
  data () {
    return {
      items: [
        { icon: 'mdi-account-circle', title: 'Profil', link: '/profile' },
        { icon: 'mdi-exit-to-app', title: 'Se déconnecter', link: '/' }
      ]
    }
  },
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
