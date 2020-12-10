<template>
  <v-container>
    <v-row justify="center" align="center">
      <v-col md="6">
        <v-card>
          <v-card-title>
            Utilisateur enregistré !
          </v-card-title>
          <v-card-text>
            <p>Votre inscription a bien été prise en compte !</p>
            <v-btn to="/" color="success">Revenir à la page d'accueil</v-btn>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'InscriptionConfirmation'
}
</script>
