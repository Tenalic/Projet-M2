<template>
  <v-container fill-height>
    <v-row align="center" justify="center">
      <v-col md="6">
        <v-card outlined>
          <v-card-title>
            Saisir votre code
          </v-card-title>
          <v-card-text>
            <v-form ref="loginForm" v-model="valid">
              <v-container>
                <v-row justify="start">
                  <v-col md="10">
                    <v-text-field clearable dense outlined required v-model="code" :rules="emailRules" label="Votre code"></v-text-field>
                  </v-col>
                </v-row>
                <v-row justify="start">
                  <v-col>
                    <v-btn :disabled="!valid" color="success" class="mr-2" @click="submit">Valider</v-btn>
                  </v-col>
                </v-row>
              </v-container>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
// @ is an alias to /src
import axios from 'axios'

export default {
  name: 'EnterCode',
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
