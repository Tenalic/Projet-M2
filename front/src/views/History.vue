<template>
  <v-container fill-height>
    <v-row align="center" justify="center">
      <v-col md="6">
        <v-card outlined>
          <v-card-title>
            History 
          </v-card-title>
          
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
// @ is an alias to /src
import axios from 'axios'

export default {
  name: 'History',
  data () {
    return {
      code: ''
    }
  },
  methods: {
    submit () {
      axios.post()
    }
  }
}
</script>
