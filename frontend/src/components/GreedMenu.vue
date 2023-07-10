<template>
  <div v-for="menuObject in menu" :key="menuObject.id">
    <div v-if="updateAlert && !menuObject.hidden">
      <input @change="updateMenu(menuObject)" v-model="menuObject.menuName" style="border: none; width: 8rem; margin-right: 5px">
      <svg @click="deleteMenu(menuObject, menu)" style="color: orangered" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-circle-fill" viewBox="0 0 16 16">
        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1h-7z"/>
      </svg>
    </div>
    <a href="javascript:void(0)" v-else-if="!menuObject.hidden" >
      {{menuObject.menuName}}
    </a>
  </div>
  <svg v-if="updateAlert" @click="plusMenu" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
  </svg>
</template>

<script>
import {computed, onMounted, ref} from "vue";
import api from "@/axios";
import {useRoute} from "vue-router";
import {useStore} from "vuex";
import {alertEvent} from "@/composables/alertEvent";

export default {
  props:{
    updateBlog : {
      type: Boolean,
      required: false,
    }
  },
  setup(props, { emit }){
    const route = useRoute();
    const store = useStore();
    const {setMessage, setTimeAlert} = alertEvent(store);
    const menu = ref(null);
    const menuCnt = ref();
    const updateAlert = computed(() => {
      if (props.updateBlog){
        setTimeAlert(true);
        setMessage("v 버튼을 누르면 변경사항이 저장됩니다.");
      }
      return props.updateBlog
    })

    onMounted(async () => {
      try {
        const username = route.params.id;
        const response = await api.post('/api/selectMenu', { username : username });
        menu.value = response.data.result;
        menuCnt.value = response.data.result.length;
      } catch (e) {
        setTimeAlert(true);
        setMessage("에러가 발생하였습니다.");
      }
    });

    const deleteMenu = (menuObject) => {
      if (menuCnt.value <= 1) {
        setTimeAlert(true);
        setMessage("메뉴 하나 이상은 필수 입니다.");
      } else {
        menuCnt.value--;
        menuObject.hidden = true;
        emit('deleteMenu', );
      }
    }

    const updateMenu = (menuObject) => {
      console.log(menuObject.id);
      console.log(menuObject.menuName)
    }

    const plusMenu = () => {
      menu.value.push({
        id: Date.now(), // 가짜 ID, 실제 구현에서는 유니크한 ID를 제공해야 함
        menuName: 'menu',   // 초기 메뉴 이름
        hidden: false,  // 초기 hidden 값
      });
      menuCnt.value++;
    }

    return {
      setMessage, setTimeAlert, menu, deleteMenu, updateAlert, plusMenu, updateMenu
    }
  }
}

</script>

<style>

</style>