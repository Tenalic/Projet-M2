<template>
  <v-container v-if="!loading">
      <v-row justify="center">
        <!-- PLATEAU -->
        <v-sheet
        v-if="board"
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
                        on affiche les coordonnées de la rue -->
                  <v-card v-if="displayBoard[i-1][j-1] != null"
                  tile
                  outline
                  v-bind:style="{'background-color' : displayBoard[i-1][j-1].color}"
                  v-bind:id = displayBoard[i-1][j-1].index
                  height="100"
                  width="100">
                    {{displayBoard[i-1][j-1].index}}  <!-- id pour se repérer, à enlever plus tard -->
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
        <v-divider></v-divider>
        <v-treeview></v-treeview>
        </v-card>
      </v-row>
      <!-- BOUTON LANCER DE DES -->
      <v-btn id="diceToss" @click="rollTheDices">
        <v-icon small>fas fa-dice-five</v-icon>{{account.nbDice}}
        Lancez vos dés
      </v-btn>
      <!-- BOUTON ENTRER UN CODE -->
      <v-btn>
        Entrer un code
      </v-btn>
      <!-- CARTE D'INFOS SUR LA RUE -->
      <v-card>
      <v-progress-linear value="100" :color="board[piece.index].color"/>
      <v-card-title>
        {{ board[piece.index].index }} {{ board[piece.index].streetName }}
      </v-card-title>
      <v-card-subtitle>
        Valeur : {{ board[piece.index].cost }}€
      </v-card-subtitle>
      <v-btn :id="buyButton" @click="buy(piece.index)" :disabled="!canBuy">Acheter</v-btn>
      <v-btn>Refuser</v-btn>
      </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'GameView',
  data () {
    return {
      // Utilisateur
      account: {
        nickname: 'Nono',
        credit: 50,
        nbDice: 2
      },
      board: null,
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
      // Pion
      piece: {
        icon: 'fas fa-chess-pawn',
        index: -1
      },
      loading: true
    }
  },
  mounted () {
    this.getBoard()
    this.piece.index = 0
  },
  methods: {
    /*
    * Création et affichage du plateau
    */
    // Réception du plateau depuis l'API
    async getBoard () {
      await axios
        .get('http://monopolym2tnsi.hopto.org:8080/board')
        .then(response => { this.board = response.data.board; this.setDiplayBoard() })
        .catch(error => console.log(error))
    },
    // Création du tableau displayBoard
    setDiplayBoard () {
      let i = 0
      let n = 5
      let m = 5
      for (m = 5; m >= 0; m--) {
        console.log(i + ' ' + this.board[i])
        this.displayBoard[n][m] = this.board[i]
        i += 1
        console.log(this.board[i])
      }
      m = 0
      for (n = 4; n >= 0; n--) {
        console.log(i + ' ' + this.board[i])
        this.displayBoard[n][m] = this.board[i]
        i += 1
      }
      n = 0
      for (m = 1; m < 6; m++) {
        console.log(i + ' ' + this.board[i])
        this.displayBoard[n][m] = this.board[i]
        i += 1
      }
      m = 5
      for (n = 1; n < 5; n++) {
        console.log(i + ' ' + this.board[i])
        this.displayBoard[n][m] = this.board[i]
        i += 1
      }
      console.log(this.displayBoard)
      this.loading = false
    },
    // Lance les 2 dés
    async rollTheDices () {
      // APPEL API
      this.diceToss = Math.floor(Math.random() * (12 - 2 + 1) + 2)
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
    buy (index) {
      this.account.credit -= this.board[index].cost
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
    // Retourne si le joueur a assez d'argent pour payer
    canBuy () {
      return (this.account.credit >= this.board[this.piece.index].cost)
    }
  }
}
</script>
