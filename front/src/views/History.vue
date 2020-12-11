<template>
  <v-container v-if="user" class="grey lighten-5" fill-height>
    <v-row >
    <v-col>
    <h2 class="text-center">Liste des cadeaux obtenus</h2>
    </v-col>
    </v-row>
    <v-row>
      <v-col v-for="(prize,index) in user.prize" :key="index" cols="4">
        <v-card>
          <v-row align="center">
            <v-col align="center">
              <v-icon size="30" color="grey">mdi-gift</v-icon>
            </v-col>
            <v-col>
            {{prize}}
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
// @ is an alias to /src
import axios from 'axios'
import { mapGetters } from 'vuex'

export default {
  name: 'History',
  data () {
    return {
      code: ''
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
  methods: {
    submit () {
      axios.post()
    }
  }
}
</script>
