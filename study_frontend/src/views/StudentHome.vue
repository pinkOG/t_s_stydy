<template>
  <div id="app">
    <HeaderComponent />
    <MiddleComponent />
    <BottomComponent />
  </div>
</template>

<script>
import HeaderComponent from '../components/HeaderComponent.vue'
import MiddleComponent from '../components/MiddleComponent.vue'
import BottomComponent from '../components/BottomComponent.vue'

export default {
  name: 'StudentHome',
  components: {
    HeaderComponent,
    MiddleComponent,
    BottomComponent
  }
}
</script>

<style scoped>
#app {
  font-family: Arial, sans-serif;
  color: #333;
}
</style>