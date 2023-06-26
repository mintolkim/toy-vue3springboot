<!-- AlertModal.vue -->
<template>
  <div class="alert alert-primary" role="alert">
    {{ message }}
  </div>
</template>

<script>
export default {
  props: {
    message: {
      type: String,
      default: ''
    }
  }
}
</script>

<style>
.alert {
  position: fixed;
  top: 0;
  width: 100%;
}

</style>
