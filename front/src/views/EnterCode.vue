<template>
  <v-container fill-height>
    <v-row align='center' justify='center'>
      <v-col md='6'>
        <v-card outlined>
          <v-card-title>
            Saisir votre code
          </v-card-title>
          <v-card-text>
            <v-form ref='code' v-model='valid'>
              <v-container>
                <v-row justify='start'>
                  <v-col md='10'>
                    <v-text-field clearable dense outlined required v-model='code' :rules='codeRules' label='Votre coupon'></v-text-field>
                    <h1 v-if="couponNonValide"> Coupon non valide </h1>
                  </v-col>
                </v-row>
                <v-row justify='start'>
                  <v-col>
                    <v-btn :disabled='!valid' color='success' class='mr-2' @click='get_prizes'>Valider</v-btn>
                  </v-col>
                </v-row>
              </v-container>
              <!-- list prizes container -->
              <!-- just won in GREEN -->
              <v-container v-if="finalPrize" fluid mt-5>
                <v-card>
                  <v-row align="center" justify="center">
                    <v-col md="6">
                      <v-card-title>
                        You won {{ finalPrize.prizeWin }}
                      </v-card-title>
                    </v-col>
                  </v-row>
                </v-card>
              </v-container>

              <v-container v-if="isListPrize" fluid mt-5>
                <v-row align="center" justify="center" v-for="p in this.prize3.prize" :key="p.MenuSelected">
                  <v-col md="6">
                    <v-hover v-slot:default="{ hover }">
                    <v-card
                    color="#d75838"
                    :elevation="hover ? 16 : 2"
                    class="menuTitle"
                    width="60%"
                    >
                      <v-card-title primary-title class="layout justify-center">
                      <!-- <v-card-text> -->
                        <div> {{p}} </div>
                      <!-- </v-card-text> -->
                      </v-card-title>
                    </v-card>
                    </v-hover>
                  </v-col>
                </v-row>
              </v-container>
              <!-- list prizes container -->

              <!-- simple prize container -->
              <v-container v-if="isSimplePrize" fluid mt-5>
                <v-row align="center" justify="center">
                  <v-col md="6">
                    <v-card>
                      <v-card-title>simple</v-card-title>
                    </v-card>
                  </v-col>
                </v-row>
              </v-container>
              <!-- simple prize container -->
              <!-- credit prize container -->
              <v-container v-if="isCreditPrize" fluid mt-5>
                <v-row align="center" justify="center">
                  <v-col md="6">
                    <v-card>
                      <v-card-title>credit</v-card-title>
                    </v-card>
                  </v-col>
                </v-row>
              </v-container>
              <!-- credit prize container -->
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
      label: 'Votre coupon',
      couponNonValide: false,
      validCod: true,
      valid: true,
      code: '',
      codeRules: [
        v => /^[0-9]/.test(v) || 'Seuls les chiffres sont acceptés'
      ],
      prize1: {
        nbDice: 988
      },
      prize2: {
        nbDice: 989,
        creditWin: 50,
        credit: 9750
      },
      prize3: {
        nbDice: 991,
        prizeWin: 'prize2',
        prize: [
          'prize4',
          'prize1',
          'prize7'
        ]
      },
      prizeError: {
        message: 'Error: ça va pas le faire'
      },
      requestRes: null,
      // received object
      finalPrize: null,
      isSimplePrize: true,
      isCreditPrize: true,
      isListPrize: true
    }
  },
  methods: {
    render () {

    },
    render_credit () {

    },
    render_prizes () {

    },
    render_error () {

    },
    get_prizes () {
      console.log('get_prizes of ', this.code)
      // var obj = null
      // test code 9559948 or 4430164 or 3030553

      const config = {
        headers: {
          IdAccount: 180
        }
      }
      console.log('confog ', config)
      const data = {
      }
      // var obj = null
      // var headers = { IdAccount: 310 }
      var url = 'http://monopolym2tnsi.hopto.org:8080/code/use/' + this.code
      axios.post(url, data, config)
        .then(res => {
          console.log('post.then ', res.data)
          var obj = res.data
          if ('message' in obj) {
            this.render_error()
          }
          if ('credit' in obj) {
            this.render_credit()
          }
          if ('prize' in obj) {
            this.render_prizes()
          }
          if (!('credit' in obj) && !('prize' in obj) && !('message' in obj)) {
            this.render()
          }
        })
        .catch(err => console.log('error ', err))
    }
  }
}
</script>
<style scoped>
.menuTitle {
  cursor: pointer;
}
</style>
