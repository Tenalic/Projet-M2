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
                    <v-text-field dense outlined required v-model='code' :rules='codeRules' label='Votre coupon'></v-text-field>
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
              <v-container v-if="isListPrize" >
                <v-card class="text--centered">
                  <v-row align="center" justify="center">
                    <v-col md='6' align="center" >
                      <v-icon size="120" color="green">mdi-gift</v-icon>
                    </v-col>
                  </v-row>
                  <v-row align="center" justify="center">
                    <v-col md="8" align="center">
                      <v-card-text class="text--centered">Vous avez gagné {{finalPrize.prizeWin}}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </v-container>
              <!-- list  -->
              <v-container v-if="isListPrize" class="grey lighten-5">
                <v-row>
                  <v-col v-for="(prize,index) in finalPrize.prize" :key="index" cols="4">
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

              <v-container v-if="isListPrize" fluid mt-5>
                  <v-row align="center" justify="center">
                    <v-card color="green" width="40%">
                      <v-card-title>
                        {{ finalPrize.prizeWin }}
                      </v-card-title>
                    </v-card>
                    <v-col md="6">
                    </v-col>
                  </v-row>
              </v-container>

              <v-container v-if="isListPrize" fluid mt-5>
                <v-row align="center" justify="center" v-for="p in this.finalPrize.prize" :key="p.MenuSelected">
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
              <!-- list prizes container END-->

              <!-- simple prize container -->
              <v-container v-if="isSimplePrize" >
                <v-card class="text--centered">
                  <v-row align="center" justify="center">
                    <v-col md='6' align="center" >
                      <v-icon size="120" color="green">mdi-check</v-icon>
                    </v-col>
                  </v-row>
                  <v-row align="center" justify="center">
                    <v-col md="8" align="center">
                      <v-card-text class="text--centered">Vous avez gagné 1 lancer</v-card-text>
                    </v-col>
                  </v-row>
                  <v-row align="center" justify="center">
                    <v-col style="color: gray" align="center" md="4">
                      <v-card-text>Total {{finalPrize.nbDice}}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </v-container>
              <!-- simple prize container END-->

              <!-- credit prize container -->
              <!-- lancer -->
              <v-container v-if="isCreditPrize">
                <v-card class="text--centered">
                  <v-row align="center" justify="center">
                    <v-col md='6' align="center" >
                      <v-icon size="120" color="green">mdi-check</v-icon>
                    </v-col>
                  </v-row>
                  <v-row align="center" justify="center">
                    <v-col md="8" align="center">
                      <v-card-text class="text--centered">Vous avez gagné 1 lancer</v-card-text>
                    </v-col>
                  </v-row>
                  <v-row align="center" justify="center">
                    <v-col style="color: gray" align="center" md="4">
                      <v-card-text>Total {{finalPrize.nbDice}}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </v-container>
              <!-- credit -->
              <v-container v-if="isCreditPrize">
                <v-card class="text--centered">
                  <v-row align="center" justify="center">
                    <v-col md='6' align="center" >
                      <v-icon size="120" color="green">mdi-cash-usd-outline</v-icon>
                    </v-col>
                  </v-row>
                  <v-row align="center" justify="center">
                    <v-col md="8" align="center">
                      <v-card-text class="text--centered">Vous avez gagné {{finalPrize.creditWin}} credits</v-card-text>
                    </v-col>
                  </v-row>
                  <v-row align="center" justify="center">
                    <v-col style="color: gray" align="center" md="4">
                      <v-card-text>Total {{finalPrize.credit}}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </v-container>
              <!-- credit prize container END-->
              <!-- error prize container -->
              <v-container v-if="isErrorPrize" >
                <v-card >
                  <v-row align="center" justify="center">
                    <v-col md='6' align="center" >
                      <v-icon size="120" color="red">mdi-close</v-icon>
                    </v-col>
                  </v-row>
                  <v-row >
                    <v-col align="center" md="4">
                      <v-card-text>{{finalPrize.message}}</v-card-text>
                    </v-col>
                  </v-row>
                </v-card>
              </v-container>
              <!-- error prize container END-->
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
      prize_simple: {
        nbDice: 988
      },
      prize_credit: {
        nbDice: 989,
        creditWin: 50,
        credit: 9750
      },
      prize_list: {
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

      isSimplePrize: false,
      isCreditPrize: false,
      isListPrize: false,
      isErrorPrize: false
    }
  },
  methods: {
    set_prizes_fale () {
      this.isSimplePrize = false
      this.isCreditPrize = false
      this.isListPrize = false
      this.isErrorPrize = false
    },
    render_simple () {
      console.log('SIMPLE')
      this.isSimplePrize = true
    },
    render_credit () {
      console.log('CREDIT')
      this.isCreditPrize = true
    },
    render_prizes () {
      console.log('LIST')
      this.isListPrize = true
    },
    render_error () {
      console.log('ERROR')
      this.isErrorPrize = true

      var text = this.finalPrize.message
      this.finalPrize.message = text.split(':')[1]
    },
    get_prizes () {
      console.log('get_prizes of ', this.code)
      // axios.get()
      // var obj = null
      // test code 9559948 or 4430164 or 3030553
      // code 9702578 or 400
      var config = {
        headers: {
          IdAccount: 307
        }
      }
      var data = {
      }
      var url = 'http://monopolym2tnsi.hopto.org:8080/code/use/' + this.code
      axios.post(url, data, config)
        .then(res => {
          console.log('post.then ', res.data)
          var obj = res.data
          this.finalPrize = obj

          this.set_prizes_fale()

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
            this.render_simple()
          }
        })
        .catch(err => console.log('error delphin', err.message))
    }
  }
}
</script>
<style scoped>
.menuTitle {
  cursor: pointer;
}
</style>
