<template>
  <div class="text-center">
    <div class="form-signin w-25 p-3 d-inline-block">
      <img class="img-fluid" src="../bloghome.png" alt="" >
      <h1 class="h3 mb-3 fw-normal ">Welcome to Join</h1>
      <div class="form-floating mb-2">
        <input type="text" class="form-control" id="floatingInput" placeholder="Id" v-model="username">
        <label for="floatingInput">Id</label>
      </div>
      <div class="form-floating mb-2">
        <div></div>
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="password">
        <label for="floatingPassword">Password</label>
      </div>
      <div class="form-floating mb-2">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword">Password</label>
      </div>
      <div class="form-floating mb-2">
        <input type="text" class="form-control" id="floatingInput" placeholder="Address" name="Address" v-model="city">
        <label for="floatingInput">Address</label>
      </div>
      <button class="w-100 btn btn-lg btn-primary" type="button" @click="onsubmit">Sign Up</button>
      <p class="mt-5 mb-3 text-muted">© Today's Blog</p>
    </div>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";
import api from "@/axios";
import {useRouter} from "vue-router";
export default {
  setup(){
    const router = useRouter();
    const userData = reactive({
      username: '',
      password: '',
      city: ''
    })

    const onsubmit = async () => {
      await api.post('/api/join', userData)
          .then(response => {
            console.log(response.data);
            if(response.data.status === '200'){
              router.push({name: 'home', params: {userData : response.data}});
            } else {
              console.log(response.data.message);
            }
          })
          .catch(error => {
            console.error(error);
          });
    }

    return{
      ...toRefs(userData), onsubmit
    }
  }
}
</script>

<style>

</style>