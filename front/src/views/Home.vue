<template>
  <div align="center">
  <v-carousel
    cycle
    height="250"
    hide-delimiter-background
    show-arrows-on-hover
    interval="3000"
  >
    <v-carousel-item
      v-for="(slide, i) in slides"
      :key="i"
    >
      <v-sheet
        :color="colors[i]"
        height="100%"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-3">
            {{ slide }}
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
<v-container grid-list-md text-xs-center style="margin-top:3%">
      <v-row align="center">
        <v-flex xs6 offset-xs3 v-for="select in team" :key="select.MenuSelected" >
          <v-hover v-slot:default="{ hover }">
          <v-card
          color="#d75838"
          :elevation="hover ? 16 : 2"
          class="menuTitle"
          width="55%"
          >
            <v-card-title primary-title class="layout justify-center">
            <v-car-text>
              <div> {{select.MenuSelected}} </div>
            </v-car-text>
            </v-card-title>
          </v-card>
          </v-hover>
        </v-flex>
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
        { MenuSelected: 'allo' },
        { MenuSelected: 'allo' },
        { MenuSelected: 'allo' },
        { MenuSelected: 'allo' },
        { MenuSelected: 'allo' }
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
  margin: 3%;
  cursor: pointer;
}

.v-card {
  color: whitesmoke !important;
}

</style>
