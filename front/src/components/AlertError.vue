<template>
  <v-alert dismissible type="error" @input="onClose" :value="true" tile>
    {{ text }}
  </v-alert>
</template>

<script>
export default {
  props: ['text'],
  methods: {
    onClose () {
      this.$emit('dismissed')
    }
  }
}
</script>
