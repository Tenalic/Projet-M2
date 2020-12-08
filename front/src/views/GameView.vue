<template>
  <v-container>
    <v-row>
      <!-- PLATEAU -->
      <v-sheet
      color="white"
      elevation="20"
      height="600"
      width="600">
          <v-row
          v-for="i in 6"
          :key="i"
          no-gutters>
            <v-col
            v-for="j in 6"
            :key="j"
            no-gutters>
                <!-- Si le contenu de displayBoard à l'index i,j n'est pas nul,
                      on affiche les coordonnées de la ville -->
                <v-card v-if="displayBoard[i-1][j-1] != null"
                tile
                outline
                v-bind:style="{'background-color' : displayBoard[i-1][j-1].color}"
                height="100"
                width="100">
                  {{displayBoard[i-1][j-1].streetName}}
                </v-card>
            </v-col>
          </v-row>
      </v-sheet>
      <!-- INFOS JOUEURS -->
      <v-card>
        <v-card-title>
          {{ account.nickname }}
        </v-card-title>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'GameView',
  data () {
    return {
      // Utilisateur
      account: {
        nickname: 'Nono',
        credit: '1000',
        board: [
          {
            id: '0',
            streetName: 'Avenue des Champs-Elysées',
            color: 'white',
            cost: '50',
            bought: false
          },
          {
            id: '1',
            streetName: 'Rue de la Paix',
            color: 'red',
            cost: '100',
            bought: false
          },
          {
            id: '2',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '3',
            streetName: 'Avenue des Champs-Elysées',
            color: 'green',
            cost: '50',
            bought: false
          },
          {
            id: '4',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '5',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '6',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '7',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '8',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '9',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '10',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '11',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '12',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '13',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '14',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '15',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '16',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '17',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '18',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          },
          {
            id: '19',
            streetName: 'Avenue des Champs-Elysées',
            color: 'blue',
            cost: '50',
            bought: false
          }
        ]
      },
      // Tableau pour l'affichage du plateau
      displayBoard: [
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null]
      ]
    }
  },
  beforeMount () {
    this.setDiplayBoard()
  },
  methods: {
    // Création du tableau displayBoard
    setDiplayBoard () {
      let i = 0
      let n = 5
      let m = 5
      for (m = 5; m >= 0; m--) {
        this.displayBoard[n][m] = this.account.board[i]
        i += 1
      }
      m = 0
      for (n = 4; n >= 0; n--) {
        this.displayBoard[n][m] = this.account.board[i]
        i += 1
      }
      n = 0
      for (m = 1; m < 6; m++) {
        this.displayBoard[n][m] = this.account.board[i]
        i += 1
      }
      m = 5
      for (n = 1; n < 5; n++) {
        this.displayBoard[n][m] = this.account.board[i]
        i += 1
      }
      console.log(this.displayBoard)
    }
  }
}
</script>
