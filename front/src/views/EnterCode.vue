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
      requestRes: null
    }
  },
  methods: {
    get_prizes () {
      console.log('get_prizes of ', this.code)
      // var obj = null
      // test code 9559948 or 4430164 or 3030553
      const config = {
        headers: {
          IdAccount: 123
        }
      }
      const data = {
      }

      // var obj = null
      // var headers = { IdAccount: 123 }
      var url = 'http://monopolym2tnsi.hopto.org:8080/code/use/' + this.code
      axios.post(url, data, config)
        .then(res => {
          // console.log(res.data)
          var obj = res.data
          if ('message' in obj) {
            console.log('Error: ', obj.message)
          }
          if ('credit' in obj) {
          }
          if ('prize' in obj) {
          }
          if (!('credit' in obj) && !('prize' in obj) && !('message' in obj)) {
          }
        })
        .catch(err => console.log('error ', err))
    }
  }
}
</script>
