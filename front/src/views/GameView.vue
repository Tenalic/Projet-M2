<template>
  <v-container>
    <v-row justify="center">
      <!-- PLATEAU -->
      <v-sheet
      color="#EAEDED"
      class="background-image"
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
                      on affiche les coordonnées de la rue -->
                <v-card v-if="displayBoard[i-1][j-1] != null"
                tile
                outline
                v-bind:style="{'background-color' : displayBoard[i-1][j-1].color}"
                v-bind:id = displayBoard[i-1][j-1].id
                height="100"
                width="100">
                  {{displayBoard[i-1][j-1].id}}  <!-- id pour se repérer, à enlever plus tard -->
                  {{displayBoard[i-1][j-1].streetName}}<br>
                  {{displayBoard[i-1][j-1].cost}}€
                </v-card>
            </v-col>
          </v-row>
      </v-sheet>
      <!-- INFOS JOUEURS -->
      <v-card>
        <v-card-title>
          {{ account.nickname }}
        </v-card-title>
      <v-card-subtitle>
        Argent : {{account.credit}}€
      </v-card-subtitle>
        <v-card id="dice">
          <img v-bind:src="showDiceImageD1" width="60">
          <img v-bind:src="showDiceImageD2" width="60">
        </v-card>
      </v-card>
    </v-row>
    <!-- BOUTON LANCER DE DES -->
    <v-btn id="diceToss" @click="rollTheDices">
      <v-icon small>fas fa-dice-five</v-icon>{{account.nbDice}}
      Lancez vos dés
    </v-btn>
    <!-- CARTE D'INFOS SUR LA RUE -->
    <v-card>
    <v-progress-linear value="100" :color="board[piece.index].color"/>
    <v-card-title>
      {{ board[piece.index].id }} {{ board[piece.index].streetName }}
    </v-card-title>
    <v-card-subtitle>
      {{ board[piece.index].cost }}
    </v-card-subtitle>
    <v-btn @click="buy">Acheter</v-btn>
    <v-btn>Refuser</v-btn>
    </v-card>
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
        credit: 1000,
        nbDice: 2
      },
      board: [
        {
          id: 0,
          streetName: 'Avenue des Champs-Elysées',
          color: 'white',
          cost: 50,
          bought: false
        },
        {
          id: 1,
          streetName: 'Rue de la Paix',
          color: 'red',
          cost: 100,
          bought: false
        },
        {
          id: 2,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 3,
          streetName: 'Avenue des Champs-Elysées',
          color: 'green',
          cost: 50,
          bought: false
        },
        {
          id: 4,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 5,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 6,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 7,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 8,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 9,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 10,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 11,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 12,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 13,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 14,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 15,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 16,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 17,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 18,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        },
        {
          id: 19,
          streetName: 'Avenue des Champs-Elysées',
          color: 'blue',
          cost: 50,
          bought: false
        }
      ],
      // Tableau pour l'affichage du plateau
      displayBoard: [
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null],
        [null, null, null, null, null, null]
      ],
      // Résultat du lancé de dés
      diceToss: 0,
      dice1: 0,
      dice2: 0,
      // Pion
      piece: {
        icon: 'fas fa-chess-pawn',
        index: -1
      }
    }
  },
  beforeMount () {
    this.setDiplayBoard()
    this.piece.index = 0
  },
  methods: {
    // Création du tableau displayBoard
    setDiplayBoard () {
      let i = 0
      let n = 5
      let m = 5
      for (m = 5; m >= 0; m--) {
        this.displayBoard[n][m] = this.board[i]
        i += 1
      }
      m = 0
      for (n = 4; n >= 0; n--) {
        this.displayBoard[n][m] = this.board[i]
        i += 1
      }
      n = 0
      for (m = 1; m < 6; m++) {
        this.displayBoard[n][m] = this.board[i]
        i += 1
      }
      m = 5
      for (n = 1; n < 5; n++) {
        this.displayBoard[n][m] = this.board[i]
        i += 1
      }
    },
    // Lance les 2 dés
    rollTheDices () {
      this.dice1 = Math.floor(Math.random() * 6) + 1
      this.dice2 = Math.floor(Math.random() * 6) + 1
      this.diceToss = this.dice1 + this.dice2
      this.advance()
    },
    // Faire avancer le pion
    advance () {
      if (this.piece.index < 19) this.piece.index++
      else {
        this.piece.index = 0
      }
      this.diceToss--
      if (this.diceToss !== 0) {
        this.advance()
      }
    },
    // Retourne la carte avec les informations de la rue
    getStreet () {

    },
    // Fonction achat
    buy () {

    }
  },
  watch: {
    // Met à jour la position du joueur sur le plateau (graphique)
    playerIndex () {
      document.getElementById(this.piece.index)
    }
  },
  computed: {
    // Retourne l'index du pion
    playerIndex () {
      return this.piece.index
    },
    // Retourne les images du dé en fonction du lancer
    showDiceImageD1 () {
      if (this.dice1 === 1) {
        return require('../assets/dices/dice1.png')
      }
      if (this.dice1 === 2) {
        return require('../assets/dices/dice2.png')
      }
      if (this.dice1 === 3) {
        return require('../assets/dices/dice3.png')
      }
      if (this.dice1 === 4) {
        return require('../assets/dices/dice4.png')
      }
      if (this.dice1 === 5) {
        return require('../assets/dices/dice5.png')
      }
      if (this.dice1 === 6) {
        return require('../assets/dices/dice6.png')
      }
      return null
    },
    showDiceImageD2 () {
      if (this.dice2 === 1) {
        return require('../assets/dices/dice1.png')
      }
      if (this.dice2 === 2) {
        return require('../assets/dices/dice2.png')
      }
      if (this.dice2 === 3) {
        return require('../assets/dices/dice3.png')
      }
      if (this.dice2 === 4) {
        return require('../assets/dices/dice4.png')
      }
      if (this.dice2 === 5) {
        return require('../assets/dices/dice5.png')
      }
      if (this.dice2 === 6) {
        return require('../assets/dices/dice6.png')
      }
      return null
    }
  }
}
</script>

<style scoped>
#dice {
  position: absolute;
    z-index: 6;
    left: 50%;
    top: 100px;
    transform: translateX(-50%);
    color: #232323;
    font-weight: 600;
    padding: 7px 19px;
  font-size: 19px;
}

.background-image {
  background-position: right 64% top 85%;
  background-image: url("../assets/logotnsi.png");
}
</style>
