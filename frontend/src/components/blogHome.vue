<template>
  <div class="container mb-3 text-center">
    <img src="../bloghome.png" class="img-fluid"><br>
    <div class="w-75 d-inline-block">
      <div class="input-group mb-3">
        <select class="form-select w-25" aria-label="Default select example">
          <option selected>전체</option>
          <option value="1">블로그명</option>
          <option value="2">아이디</option>
          <option value="3">닉네임</option>
        </select>
        <input type="text" class="form-control w-50" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
        <button class="btn btn-outline-secondary" type="button" id="button-addon2">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search-heart" viewBox="0 0 16 16">
            <path d="M6.5 4.482c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.69 0-5.018Z"/>
            <path d="M13 6.5a6.471 6.471 0 0 1-1.258 3.844c.04.03.078.062.115.098l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1.007 1.007 0 0 1-.1-.115h.002A6.5 6.5 0 1 1 13 6.5ZM6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11Z"/>
          </svg>
          Search
        </button>
      </div>

      <div class="container text-center">
        <div class="row text-start p-2">
          <div class="col"><h4>
            <em>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-asterisk" viewBox="0 0 16 16">
                <path d="M8 0a1 1 0 0 1 1 1v5.268l4.562-2.634a1 1 0 1 1 1 1.732L10 8l4.562 2.634a1 1 0 1 1-1 1.732L9 9.732V15a1 1 0 1 1-2 0V9.732l-4.562 2.634a1 1 0 1 1-1-1.732L6 8 1.438 5.366a1 1 0 0 1 1-1.732L7 6.268V1a1 1 0 0 1 1-1z"/>
              </svg>
              Featured Blog
            </em>
          </h4></div>
          <div class="col text-end">
            <h5>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
                <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
              </svg>
              Refresh
            </h5>
          </div>
        </div>
        <div class="row p-2">
          <div class="col-sm-8">
            <div class="card shadow-sm selheight w-100 mh-100">
              <h5 class="p-2">blog name</h5>
              <div class="card-body">
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <div class="d-flex justify-content-between align-items-center">
                </div>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="col">
              <div class="card shadow-sm selheight w-100 mh-100">
                <h5 class="p-2">blog name</h5>
                <div class="card-body">
                  <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                  <div class="d-flex justify-content-between align-items-center">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row p-2">
          <div class="col">
            <div class="col">
              <div class="card shadow-sm selheight w-100 mh-100">
                <h5 class="p-2">blog name</h5>
                <div class="card-body">
                  <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                  <div class="d-flex justify-content-between align-items-center">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="col">
              <div class="card shadow-sm selheight mh-100">
                <h5 class="p-2">blog name</h5>
                <div class="card-body">
                  <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                  <div class="d-flex justify-content-between align-items-center">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="col">
              <div class="card shadow-sm selheight mh-100">
                <h5 class="p-2">blog name</h5>
                <div class="card-body">
                  <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                  <div class="d-flex justify-content-between align-items-center">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <router-link to="/blog">blog</router-link><br>
</template>

<script>
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";

export default {
  setup(){
    const route = useRoute();
    const userData = ref(null);

    onMounted(() => {
      userData.value = route.params.userData;

    });

    return{
      userData
    }
  }
}
</script>

<style>
.selheight {
  min-height: 200px;
  width: 100%;
}
</style>