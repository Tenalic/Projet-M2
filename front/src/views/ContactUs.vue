<template>
  <v-container fluid fill-height>
    <v-row no-gutters>
      <v-col cols="12" sm="8" offset-sm="2" md="5" offset-md="2">
        <v-card rounded class="mt-1">
          <app-alert-error v-if="error" class="mb-0" @dismissed="onDismissed" :text="error.message"></app-alert-error>
          <v-toolbar dark color="primary" flat>
            <v-toolbar-title>Nous envoyer un message</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-container>
              <v-form lazy-validation ref="form" v-model="valid" @submit.prevent="onSend">
                <v-row>
                  <v-col cols="12">
                    <v-text-field prepend-icon="mdi-account" name="LastName" label="Nom" type="text" :rules="fieldRules('Nom', 30)"  v-model="lastname" outlined required></v-text-field>
                    <v-spacer></v-spacer>
                    <v-text-field prepend-icon="mdi-account" name="firstname" label="Prénom" type="text" v-model="firstname" :rules="fieldRules('Prénom', 30)" outlined required></v-text-field>
                    <v-text-field prepend-icon="mdi-email" name="Email" label="E-mail" type="email" v-model="email" :rules="fieldRules('E-mail', 50)" outlined required></v-text-field>
                    <v-text-field prepend-icon="mdi-phone" name="phoneNumber" label="Numéro de téléphone" type="tel" v-model="phoneNumber" :rules="fieldRules('Numéro de téléphone', 20)" outlined required></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-textarea prepend-icon="mdi-android-messages" name="Message" type="text" auto-grow filled label="Message" outlined v-model="message" :rules="fieldRules('Message', 500)" required></v-textarea>
                  </v-col>
                </v-row>
                <v-row no-gutters>
                  <v-col cols="12">
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="primary" :disabled="loading" :loading="loading" type="submit" value="send" large>Send</v-btn>
                    </v-card-actions>
                  </v-col>
                </v-row>
              </v-form>
            </v-container>
          </v-card-text>
        </v-card>
     </v-col>
     <v-col cols="12" sm="8" md="3" offset-sm="2" offset-md="0">
       <v-card rounded class="mt-1 ml-2 hidden-sm-and-down">
          <v-toolbar dark color="primary" flat>
            <v-toolbar-title>Nous appeler</v-toolbar-title>
          </v-toolbar>
        <v-card-text>
          <div class="text-center py-5">
            <h2 class="font-weight-bold">
              <a href="tel:+33327231278">
                <v-icon class="primary--text mr-2">mdi-phone</v-icon>
                +33 3 27 23 12 78
              </a>
            </h2>
          </div>
        </v-card-text>
       </v-card>
       <v-card rounded class="mt-1 hidden-md-and-up">
          <v-toolbar dark color="primary" flat>
            <v-toolbar-title>Nous appeler</v-toolbar-title>
          </v-toolbar>
        <v-card-text>
          <div class="text-center py-7">
            <h2 class="font-weight-bold">
              <a href="tel:+33327231278">
                <v-icon class="primary--text mr-2">mdi-phone</v-icon>
                +33 3 27 23 12 78
              </a>
            </h2>
          </div>
        </v-card-text>
       </v-card>
     </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import { store } from '@/store'
// import router from '@/router'

export default {
  name: 'ContactUs',
  data: () => ({
    loading: false,
    valid: true,
    firstname: '',
    lastname: '',
    email: '',
    phoneNumber: '',
    message: '',
    dialog: false
  }),
  watch: {
    dialog (val) {
      if (!val) return
      setTimeout(() => (this.dialog = false), 4000)
    }
  },
  computed: {
    ...mapGetters(['error']),
    fieldRules () {
      return (fieldName, maxChar) => [
        v => (!!v && !!v.trim()) || `${fieldName}  est requis`,
        v =>
          (v && v.length <= maxChar) ||
          `${fieldName} doit être inférieure à ${maxChar} characters`
      ]
    }
  },
  methods: {
    onDismissed () {
      store.commit('clearError')
    },
    onSend () {
      this.loading = true

      if (!this.$refs.form.validate()) {
        this.loading = false
        return
      }

      const contactUsForm = {
        firstname: this.firstname,
        lastname: this.lastname,
        email: this.email,
        phoneNumber: this.phoneNumber,
        message: this.message
      }

      store.dispatch('onContactUs', contactUsForm)
        .then(() => {
          this.loading = false
          console.log(contactUsForm)
          // router.push('/message-sent')
        })
        .catch((err) => {
          this.loading = false
          store.commit('setError', err)
        })
    }
  }
}

</script>

<style scoped>
a {
  text-decoration: none;
}
</style>
