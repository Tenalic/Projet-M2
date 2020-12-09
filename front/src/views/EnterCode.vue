<template>
<div align="center" justify="center">
  enter code page
  </div>
</template>
<script>
// @ is an alias to /src

export default {
  name: 'EnterCode',
  data () {
    return {
      team: [
        { title: 'Cadeaux', link: '/cadeaux' },
        { title: 'allo' },
        { title: 'allo' },
        { title: 'allo' },
        { title: 'Profile', link: '/profile' }
      ],
      colors: [
        'indigo',
        'warning',
        'pink darken-2',
        'red lighten-1',
        'deep-purple accent-4'
      ],
      slides: [
        'First',
        'Second',
        'Third',
        'Fourth',
        'Fifth'
      ]
    }
  }
}
</script>

<style scoped>
.menuTitle {
  cursor: pointer;
}

.v-card {
  color: whitesmoke !important;
}

</style>
