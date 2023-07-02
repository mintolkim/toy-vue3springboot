<template>
    <div class="container">
        <div class="my-3 row row-cols-2">
            <div class="col-6"><h1>
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-arrow-through-heart" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.854 15.854A.5.5 0 0 1 2 15.5V14H.5a.5.5 0 0 1-.354-.854l1.5-1.5A.5.5 0 0 1 2 11.5h1.793l.53-.53c-.771-.802-1.328-1.58-1.704-2.32-.798-1.575-.775-2.996-.213-4.092C3.426 2.565 6.18 1.809 8 3.233c1.25-.98 2.944-.928 4.212-.152L13.292 2 12.147.854A.5.5 0 0 1 12.5 0h3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-.854.354L14 2.707l-1.006 1.006c.236.248.44.531.6.845.562 1.096.585 2.517-.213 4.092-.793 1.563-2.395 3.288-5.105 5.08L8 13.912l-.276-.182a21.86 21.86 0 0 1-2.685-2.062l-.539.54V14a.5.5 0 0 1-.146.354l-1.5 1.5Zm2.893-4.894A20.419 20.419 0 0 0 8 12.71c2.456-1.666 3.827-3.207 4.489-4.512.679-1.34.607-2.42.215-3.185-.817-1.595-3.087-2.054-4.346-.761L8 4.62l-.358-.368c-1.259-1.293-3.53-.834-4.346.761-.392.766-.464 1.845.215 3.185.323.636.815 1.33 1.519 2.065l1.866-1.867a.5.5 0 1 1 .708.708L5.747 10.96Z"/>
                </svg>
                {{ username }}'s blog</h1></div>
        </div>
        <hr>
    </div>
    <div class="container">
        <div class="my-5 row row-cols-2 mt-20">
            <div class="col-3"><UserProfile :username="username"/></div>
            <div class="col-9 text-center">
              <WriteBlog v-if="write" @movePost="movePost"/>
              <PostBlog v-else/>
              <div class="text-end">
                <button type="button" class="btn btn-outline-primary" @click="clickEvent" v-if="username === visitUserStatus && !write">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                  </svg>
                  글 쓰기
                </button>
              </div>
            </div>
        </div>
    </div>
</template>

<script>

import PostBlog from "@/components/PostBlog.vue";
import UserProfile from "@/components/UserProfile.vue";
import {computed, ref} from "vue";
import {useRoute} from "vue-router";
import {alertEvent} from "@/composables/alertEvent";
import {useStore} from "vuex";
import WriteBlog from "@/components/WriteBlog.vue";

export default {
    components: {
        PostBlog,
        UserProfile,
        WriteBlog
    },
    setup(){
      const route = useRoute();
      const store = useStore();
      const {setMessage, setTimeAlert} = alertEvent(store);
      const write = ref(false);
      const username = route.params.id;
      const visitUserStatus = computed(() => store.state?.user?.username);

      const movePost = () => {
        write.value = false;
      }
      const clickEvent = () => {
        write.value = true;
      }


      return{
        clickEvent, setMessage, setTimeAlert, username, write, visitUserStatus, movePost
      }
  }
}
</script>

<style>
.log {
    text-align: right;
}
</style>
