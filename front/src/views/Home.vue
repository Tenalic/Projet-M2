<template>
<div align="center">
  <v-carousel cycle height="150" hide-delimiter-background show-arrows-on-hover interval="3000">
    <v-carousel-item v-for="(slide, i) in slides" :key="i">
      <v-sheet :color="colors[i]" height="100%">
        <v-row class="fill-height" align="center" justify="center">
          <div class="display-3">
            {{ slide }}
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
<v-container fluid>
      <v-row align="center" justify="center" class="mt-10">
         <v-card
          width="340"
          height="340">
          <v-img
          src="../assets/tnsi.png"
          height="200"
          >
          </v-img>
          <v-card-title> Restaurant Tnsi </v-card-title>
          <v-card-subtitle>
          Bientôt de nouveaux restaurants proche de chez vous :)
          </v-card-subtitle>
          </v-card>
        <v-col md="6">
          <v-row v-for="select in team" :key="select.link" align="center" justify="center" class="mt-10">
          <v-hover v-slot:default="{ hover }">
          <v-card color="#d75838" :elevation="hover ? 16 : 2" class="menuTitle white--text" width="45%" :to="select.link">
            <v-card-title primary-title class="layout justify-center">
            <!-- <v-card-text> -->
              <div> {{select.title}} </div>
            <!-- </v-card-text> -->
            </v-card-title>
          </v-card>
          </v-hover>
          </v-row>
        </v-col>
         <v-card
          width="340"
          height="340">
          <v-img
          src="../assets/security.png"
          height="200"
          >
          </v-img>
          <v-card-title> La sécurité de tous </v-card-title>
          <v-card-subtitle>
          Port du masque obligatoire, gel hydroalcoolique dans tous nos restaurants, respect de la distanciation sociale.
          Faites attention à vous, prenez soin de votre entourage :)
          </v-card-subtitle>
          </v-card>
      </v-row>
    </v-container>
  </div>
</template>
<script>
// @ is an alias to /src

export default {
  data () {
    return {
      team: [
        { title: 'Plateau de jeu', link: '/game' },
        { title: 'Règles du jeu', link: '/rules' }
      ],
      colors: [
        'indigo',
        'warning',
        'pink darken-2',
        'red lighten-1',
        'deep-purple accent-4',
        'black'
      ],
      slides: [
        'Découvrez notre jeu monopoly :)',
        'Convertissez vos achats en crédits',
        'Entrez vos coupons',
        'De nombreux cadeaux à gagner !',
        'Amusez-vous bien ;)',
        'Hello World !'
      ]
    }
  }
}
</script>

<style scoped>
.menuTitle {
  cursor: pointer;
}

</style>
