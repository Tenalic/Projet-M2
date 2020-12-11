<template>
  <v-container fill-height>
    <v-row align="center" justify="center">
      <v-col md="6">
        <v-card outlined>
          <v-card-title>
            Se connecter
          </v-card-title>
          <v-card-text>
            <v-form ref="form" v-model="valid">
              <v-container>
                <v-row justify="start">
                  <v-col md="10">
                    <!-- :rules="emailRules" -->
                    <v-text-field append-outer-icon="mdi-at" clearable dense outlined required v-model="email" label="E-mail"></v-text-field>
                  </v-col>
                </v-row>
                <v-row justify="start">
                  <v-col md="10">
                    <v-text-field append-outer-icon="mdi-lock-outline" clearable dense outlined required v-model="password" :rules="passwordRules" type="password" label="Mot de passe"></v-text-field>
                  </v-col>
                </v-row>
                <v-row justify="start">
                  <v-col>
                    <v-btn :disabled="!valid" :loading="loading" color="success" class="mr-2" @click="onSignIn">Se connecter</v-btn>
                    <v-btn color="error" class="mx-2" @click="reset">Annuler</v-btn>
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
import { store } from '@/store'
import router from '@/router'

export default {
  name: 'Login',
  data () {
    return {
      valid: true,
      loading: false,
      email: '',
      emailRules: [
        v => !!v || 'Un email est requis pour ce champ',
        v => /.+@.+\..+/.test(v) || 'L\'email doit être valide !'
      ],
      password: '',
      passwordRules: [
        v => !!v || 'Un mot de passe est requis pour ce champ'
      ]
    }
  },
  methods: {
    onSignIn () {
      if (this.$refs.form.validate()) {
        this.loading = true
        const credentials = { email: this.email, password: this.password }
        store.dispatch('signUserIn', credentials)
          .then(() => {
            this.loading = false
            router.push('/')
          })
          .catch(err => {
            this.loading = false
            store.commit('setError', err)
          })
      }
    },
    reset () {
      this.$refs.loginForm.reset()
    }
  }
}
</script>
