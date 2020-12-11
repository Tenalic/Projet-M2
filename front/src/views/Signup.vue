<template>
  <v-container fill-height>
    <v-row align="center" justify="center">
      <v-col md="6">
        <v-card outlined>
          <app-alert-error v-if="error" class="mb-0" @dismissed="onDismissed" :text="error.message"></app-alert-error>
          <v-card-title>
            S'inscrire
          </v-card-title>
          <v-card-text>
            <v-form ref="form" v-model="valid">
              <v-container>
                <v-row justify="start">
                  <v-col md="10">
                    <v-text-field append-outer-icon="mdi-format-text" v-model="lastname" :rules="[rules.required]" label="Nom" clearable outlined dense required></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col md="10">
                    <v-text-field append-outer-icon="mdi-format-text" v-model="firstname" :rules="[rules.required]" label="Prénom" clearable outlined dense required></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col md="10">
                    <v-text-field append-outer-icon="mdi-format-text" v-model="nickname" :rules="[rules.required]" label="Pseudonyme" clearable outlined dense required></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col md="10">
                    <v-text-field append-outer-icon="mdi-at" v-model="email" :rules="[rules.required, rules.mail]" label="Email" clearable outlined dense required></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col md="10">
                    <v-text-field append-outer-icon="mdi-lock-outline" v-model="password" :rules="[rules.required, rules.password]" type="password" label="Mot de passe" clearable outlined dense required></v-text-field>
                    <p class="password-info">Votre mot de passe doit comporter au moins :</p>
                    <p class="password-info" :class="password.length > 7 ? 'correct' : 'incorrect'">- 8 caractères</p>
                    <p class="password-info" :class="/[A-Z]/gm.test(password) ? 'correct' : 'incorrect'">- Une lettre majuscule</p>
                    <p class="password-info" :class="/[a-z]/gm.test(password) ? 'correct' : 'incorrect'">- Une lettre minuscule</p>
                    <p class="password-info" :class="/[0-9]/gm.test(password) ? 'correct' : 'incorrect'">- Un chiffre</p>
                    <p class="password-info" :class="/[.!#$%&'*+/=?^_`{|}~-]/gm.test(password) ? 'correct' : 'incorrect'">- Un caractère spécial (. ! # $ % & ' * + / = ? ^ _ ` { | } ~ -)</p>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col md="10">
                    <v-text-field append-outer-icon="mdi-lock-outline" v-model="password2" :rules="[rules.psswdMatch, rules.required]" label="Confirmation du mot de passe" :type="'password'" clearable outlined dense required></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn :disabled="!valid" @click="submit" class="mr-2" color="success">
                      S'inscrire
                    </v-btn>
                    <v-btn @click="reset" class="mx-2" color="error">
                      Annuler
                    </v-btn>
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
import { mapGetters } from 'vuex'

export default {
  name: 'Signup',
  data () {
    return {
      valid: true,
      lastname: '',
      firstname: '',
      nickname: '',
      email: '',
      password: '',
      password2: '',
      rules: {
        required: v => !!v || 'Ce champ est requis !',
        passwdMatch: () => this.password === this.password2 || 'Le mot de passe ne correspond pas',
        password: () => (this.password.length > 7 && /[A-Z]/gm.test(this.password) && /[a-z]/gm.test(this.password) && /[0-9]/gm.test(this.password) && /[.!#$%&'*+/=?^_`{|}~-]/gm.test(this.password)) || 'Le mot de passe ne respecte pas les critères !',
        mail: v => /.+@.+\..+/.test(v) || 'L\'email doit être valide !'
      }
    }
  },
  computed: {
    ...mapGetters(['error'])
  },
  methods: {
    onDismissed () {
      store.commit('clearError')
    },
    submit () {
      if (this.$refs.form.validate()) {
        const userDetails = {
          lastname: this.lastname,
          firstname: this.firstname,
          nickname: this.nickname,
          email: this.email,
          password: this.password
        }
        store.dispatch('signUserUp', userDetails)
          .then(() => {
            this.loading = false
            router.push('/welcome')
          })
          .catch(err => {
            this.isLoading = false
            store.commit('setError', err)
          })
      }
    },
    reset () {
      this.$refs.form.reset()
    }
  }
}
</script>

<style scoped>
.password-info {
  font-size: 0.8em;
}
.password-info.incorrect {
  color: red;
}
.password-info.correct {
  color: green;
}
</style>
