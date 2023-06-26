<template>
  <div v-for="menuObject in menu" :key="menuObject.id">
    <a href="#">
      {{menuObject.menuName}}
    </a>
  </div>
</template>

<script>
import {onMounted, ref} from "vue";
import api from "@/axios";
import {useRoute} from "vue-router";
import {useStore} from "vuex";
import {alertEvent} from "@/composables/alertEvent";

export default {
  setup(){
    const route = useRoute();
    const store = useStore();
    const {setMessage, setTimeAlert} = alertEvent(store);
    const menu = ref(null);
    onMounted(async () => {
      try {
        const username = route.params.id;
        const response = await api.post('/api/selectMenu', { username : username });
        menu.value = response.data.result;
        console.log("##menu.value = ");
        console.log(menu.value);
        console.log("##menu.value = ");

      } catch (e) {
        setTimeAlert(true);
        setMessage("에러가 발생하였습니다.");
        console.log(e);
        console.log('에러가 발생했습니다.');
      }
    });
    return {
      setMessage, setTimeAlert, menu
    }
  }
}

</script>

<style>

</style>