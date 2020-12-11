<template>
  <v-container v-if="!loading" fluid fill-height>

    <!-- POP UP GAIN DE PRIX -->
    <v-dialog v-model="prizeAlert" max-width="500px">
      <v-card>
        <v-card-title class="primary white--text">
          <h4>Félicitations !</h4>
          <v-spacer></v-spacer>
          <v-icon class="font-weight-black white--text" @click="prizeAlert = false">mdi-window-close</v-icon>
        </v-card-title>
        <v-card-text class="mt-4">
          <h3>Vous avez gagné : <span class="primary--text">{{prizeWin}}</span></h3>
        </v-card-text>
      </v-card>
    </v-dialog>

    <v-row justify="center">
      <!-- CARTE D'INFOS SUR LA RUE -->
      <v-card v-if="showStreetCard" width="150" height="300">
        <v-progress-linear value="100" :color="board[account.indexSquare].color"/>
        <v-card-title>
          {{ board[account.indexSquare].index }} {{ board[account.indexSquare].streetName }}
        </v-card-title>
        <v-card-subtitle>
          Valeur : {{ board[account.indexSquare].cost }}€
        </v-card-subtitle>
        <v-row>
          <!-- BOUTON ACHETER -->
          <v-tooltip :disabled="canBuy" bottom>
            <template v-slot:activator="{ on, attrs }">
              <div v-on="on" >
                <v-btn @click="buy" :disabled="!canBuy" v-bind="attrs">Acheter</v-btn>
              </div>
            </template>
            <span>Vous n'avez pas assez d'argent</span>
          </v-tooltip>
          <!-- BOUTON REFUSER -->
          <v-btn @click="refuseToBuy">Refuser</v-btn>
        </v-row>
      </v-card>
      <!-- PLATEAU -->
      <v-sheet v-if="board" color="#EAEDED" class="background-image" elevation="20" height="600" width="600" rounded>
          <v-row v-for="i in 6" :key="i" no-gutters>
            <v-col v-for="j in 6" :key="j" no-gutters>
                <!-- Si le contenu de displayBoard à l'index i,j n'est pas nul,
                      on affiche les coordonnées de la rue -->
                <v-card v-if="displayBoard[i-1][j-1] != null" tile outlined v-bind:style="{'background-color' : displayBoard[i-1][j-1].color}" v-bind:id = displayBoard[i-1][j-1].index height="100" width="100">
                  {{displayBoard[i-1][j-1].index}}  <!-- id pour se repérer, à enlever plus tard -->
                  {{displayBoard[i-1][j-1].streetName}}<br>
                  {{displayBoard[i-1][j-1].cost}}€
                  <v-icon v-if="isBought(displayBoard[i-1][j-1].index)">fas fa-home</v-icon>
                  <v-icon v-if="hasPiece(displayBoard[i-1][j-1].index)" class="white--text">fas fa-chess-pawn</v-icon>
                </v-card>
            </v-col>
          </v-row>
      </v-sheet>
      <!-- INFOS JOUEURS -->
      <v-card>
        <!-- <v-card-text>Account</v-card-text> -->
        <!-- <v-divider></v-divider> -->
        <v-card-text>
          <p class="text-h6">Compte</p>
          Name: <b>{{ account.firstname}} {{ account.lastname }}</b>
          <br>
          Pseudo: <b>{{ account.nickname }}</b>
          <br>
          Argent : <b>{{account.credit}}€</b>
        </v-card-text>
        <v-divider></v-divider>

        <!-- LISTE DES PROPRIETES -->
        <v-list>
          <v-subheader class="text-h6">Propriétés</v-subheader>
          <v-list-item v-for="i in account.indexSquarePurchased" :key = "account.indexSquarePurchased[i]">
            <v-list-item-content>
              <v-progress-linear value="100" :color="board[i].color"/>
              <v-list-item-text v-text = board[i].streetName></v-list-item-text>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-row>
          <!-- BOUTON LANCER DE DES -->
          <v-col cols="12">
          <v-tooltip :disabled="hasDices==true" bottom>
            <template v-slot:activator="{ on, attrs }">
              <div v-on="on" >
                <v-btn id="diceToss" @click="rollTheDices" :disabled="!hasDices" v-bind="attrs">
                  <v-icon small>fas fa-dice-five</v-icon>{{account.nbDice}}
                  <span>Lancez vos dés</span>
                </v-btn>
              </div>
            </template>
            <span>Vous n'avez pas assez de dés</span>
          </v-tooltip>
          <!-- BOUTON ENTRER UN CODE -->
          <v-btn to="/enter-code">
            Entrer un code
          </v-btn>
          </v-col>
        </v-row>
        <v-card v-if="diceToss" id="dice">
          <img v-bind:src="diceImage1" width="60">
          <img v-bind:src="diceImage2" width="60">
        </v-card>
        </v-card>
      </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'GameView',
  data () {
    return {
      // Utilisateur
      account: null,
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
      diceToss: null,
      // Chargement
      loading: true,
      // Affichage de la carte de la rue (Acheter/Refuser)
      showStreetCard: false,
      prizeWin: null,
      prizeAlert: false
    }
  },
  mounted () {
    this.account = this.$store.getters.user
    console.log('user: ', this.account)
    this.getBoard()
  },
  methods: {
    /*
    * Création et affichage du plateau
    */
    // Réception du plateau depuis l'API
    getBoard () {
      axios
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
      this.loading = false
    },
    // Renvoie l'image du dé
    getDiceImage (n) {
      switch (n) {
        case 1: return require('../assets/dices/dice1.png')
        case 2: return require('../assets/dices/dice2.png')
        case 3: return require('../assets/dices/dice3.png')
        case 4: return require('../assets/dices/dice4.png')
        case 5: return require('../assets/dices/dice5.png')
        case 6: return require('../assets/dices/dice6.png')
        default: return null
      }
    },
    /*
    * Booléens cases
    */
    // Renvoie à une case si elle est achetée (affichage de l'icône maison)
    isBought (index) {
      return this.account.indexSquarePurchased.includes(index)
    },
    // Renvoie si le joueur est sur la case
    hasPiece (index) {
      return this.account.indexSquare === index
    },
    setShowStreetCard () {
      if (this.board[this.account.indexSquare].color === '#363535' ||
      this.account.indexSquarePurchased.includes(this.board[this.account.indexSquare].index)) this.showStreetCard = false
      else this.showStreetCard = true
    },
    refuseToBuy () {
      this.showStreetCard = false
    },
    /*
    * Jeu
    */
    // Lance les 2 dés
    async rollTheDices () {
      await axios
        .post('http://monopolym2tnsi.hopto.org:8080/account/dice',
          {},
          { headers: { IdAccount: this.account.id } })
        .then(response => {
          // On affecte les variables de la réponses
          this.showStreetCard = false
          this.account.nbDice = response.data.nbDice
          this.account.indexSquare = response.data.indexSquare
          this.diceToss = response.data.diceResult
          this.setShowStreetCard()
        })
        .catch(error => console.log(error))
    },
    // Fonction achat
    async buy () {
      await axios
        .post('http://monopolym2tnsi.hopto.org:8080/account/buy',
          {},
          { headers: { IdAccount: this.account.id } })
        .then(response => {
          this.showStreetCard = false
          this.account.credit = response.data.credit
          this.account.indexSquarePurchased = response.data.indexSquarePurchased
          if (response.data.prizeWin != null) {
            this.prizeWin = response.data.prizeWin
            this.prizeAlert = true
          }
        })
    }
  },
  computed: {
    /*
    * Booléens boutons
    */
    // Retourne si le joueur a assez d'argent pour payer
    canBuy () {
      return (this.account.credit >= this.board[this.account.indexSquare].cost)
    },
    // Retourne si le joueur a assez de dés pour jouer
    hasDices () {
      return (this.account.nbDice > 1)
    },
    /*
    * Images dés
    */
    // Retourne l'image du dé 1
    diceImage1 () {
      return this.getDiceImage(this.diceToss[0])
    },
    // Retourne l'image du dé 2
    diceImage2 () {
      return this.getDiceImage(this.diceToss[1])
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
