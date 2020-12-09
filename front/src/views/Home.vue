<template>
<div align="center">
  <v-carousel cycle height="250" hide-delimiter-background show-arrows-on-hover interval="3000">
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
<v-container fluid mt-5>
      <v-row align="center" justify="center" v-for="select in team" :key="select.MenuSelected">
        <v-col md="6">
          <v-hover v-slot:default="{ hover }">
          <v-card color="#d75838" :elevation="hover ? 16 : 2" class="menuTitle" width="55%" :to="select.link">
            <v-card-title primary-title class="layout justify-center">
            <!-- <v-card-text> -->
              <div> {{select.title}} </div>
            <!-- </v-card-text> -->
            </v-card-title>
          </v-card>
          </v-hover>
        </v-col>
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
        { title: 'Cadeaux', link: '/cadeaux' },
        { title: 'allo' },
        { title: 'allo' },
        { title: 'allo' },
        { title: 'Profile', link: '/profile' }
      ],
      colors: [
        'indigo',
        'warning',
        'pink darken-2',
        'red lighten-1',
        'deep-purple accent-4'
      ],
      slides: [
        'First',
        'Second',
        'Third',
        'Fourth',
        'Fifth'
      ]
    }
  }
}
</script>

<style scoped>
.menuTitle {
  cursor: pointer;
}

.v-card {
  color: whitesmoke !important;
}

</style>
