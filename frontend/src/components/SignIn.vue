<template>
  <div class="text-center">
    <div class="form-signin w-25 p-3 d-inline-block">
      <form>
        <img class="img-fluid" src="../bloghome.png" alt="" >
        <h1 class="h3 mb-3 fw-normal ">Please sign in</h1>
        <div class="mb-3">
          <router-link to="/signup">
            sign up
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 16 16">
              <path d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2h.5a.5.5 0 0 1 .5.5V15h-1V2zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
            </svg>
          </router-link>
        </div>
        <div class="form-floating">
          <input type="text" class="form-control" id="floatingInput" v-model="username">
          <label for="floatingInput">User Id</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="password">
          <label for="floatingPassword">Password</label>
        </div>

        <div class="checkbox mb-3">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="button" @click="onSubmit" >Sign in</button>
        <p class="mt-5 mb-3 text-muted">© Today's Blog</p>
      </form>
    </div>
  </div>
</template>

<script>
import api from "@/axios";
import {reactive, toRefs} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {alertEvent} from "@/composables/alertEvent";

export default {
  setup(){
    const userData = reactive({
      username: '',
      password: ''
    });

    const store = useStore();
    const {setTimeAlert, setMessage} = alertEvent(store);
    const router = useRouter();
    const onSubmit = async () => {
      await api.post('/api/login', userData)
          .then(response => {
            if(response.data.status === '200'){
              router.push({name: 'home'});
            }
            setTimeAlert(true);
            setMessage(response.data.result.username + " 님 환영합니다.");
            console.log("###############")
            console.log(response.data);
            console.log("###############")
            store.commit('setUser',{id : response.data.result.id ,username : response.data.result.username, nickname: response.data.result.nickname});

          })
          .catch(error => {
            console.error(error);
          });
    }
    return{
      onSubmit, ...toRefs(userData), setMessage, setTimeAlert
    }
  }
}

</script>


<style>

</style>