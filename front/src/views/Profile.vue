<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="6" offset-md="3">
        <v-card color="grey lighten-4" class="pa-4">
          <v-row>
            <v-col cols="6">
              <v-avatar class="avatar primary white--text">
               {{ user.firstname | firstChar }}
               {{ user.lastname | firstChar }}
              </v-avatar>
            </v-col>
            <v-col cols="6" >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-icon color="primary" @click="edit = !edit">mdi-pencil</v-icon>
              </v-card-actions>
            </v-col>
          </v-row>
          <div v-if="!edit">
            <v-row>
              <v-col cols="12" >
                <div class="grey--text darken-4">Nickname</div>
                <span class="mr-3">{{user.nickname}}</span>

                <div class="grey--text darken-4 mt-3">Name</div>
                <span class="mr-3">{{user.firstname}}</span>
                <span>{{user.lastname}}</span>

                <div class="grey--text darken-4 mt-3">E-mail</div>
                <span class="mr-3">{{user.email}}</span>
              </v-col>
            </v-row>
          </div>
          <v-form v-else @submit.prevent="onUpdateProfile" ref="form" v-model="valid" class="mt-3">
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="user.nickname" label="Nickname" outlined dense required></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field v-model="user.firstname" label="First Name" outlined dense required></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field v-model="user.lastname" label="Last Name" outlined dense required></v-text-field>
              </v-col>
            </v-row>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn class="grey darken-2" dark @click="edit = false" depressed>Cancel</v-btn>
              <v-btn class="primary" depressed type="submit">Save</v-btn>
            </v-card-actions>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import { store } from '@/store'

export default {
  data () {
    return {
      valid: true,
      edit: false
    }
  },

  computed: {
    ...mapGetters(['user'])
  },

  methods: {
    onUpdateProfile () {
      if (this.$refs.form.validate()) {
        const userDetails = {
          lastname: this.lastname,
          firstname: this.firstname,
          nickname: this.nickname
        }
        store.dispatch('signUserUp', userDetails)
          .then(() => {
            this.loading = false
          })
          .catch(err => {
            this.isLoading = false
            store.commit('seterror', err)
          })
      }
    }
  },

  filters: {
    firstChar (value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase()
    }
  }

}
</script>

<style scoped>
.avatar {
  border-radius: 100% !important;
}

</style>
