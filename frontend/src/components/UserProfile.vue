<template>
    <div class="p-4 mb-3 bg-light rounded text-center">
        <div class="mb-3 row row-cols-1">
            <div class="mb-3 col">
                <div class="text-center">
                    <img :src="profileImg" class="img-thumbnail" alt="...">
                </div>
              <div class="text-end">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-plus" viewBox="0 0 16 16" v-if="updateBlog" @click="uploadProfile">
                  <path d="M8 6.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V11a.5.5 0 0 1-1 0V9.5H6a.5.5 0 0 1 0-1h1.5V7a.5.5 0 0 1 .5-.5z"/>
                  <path d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5L14 4.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h-2z"/>
                </svg>
              </div>
            </div>
            <div class="col">
                <h4 class="fst-italic" v-if="!updateAlert"> &nbsp;
                  {{ nickName }}
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16" v-if="blogRoute == updateStatus && !updateBlog" @click="setBlog">
                    <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
                    <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
                    </svg>
                </h4>
              <h4 v-else class="text-center fst-italic" style="display: flex; justify-content: center; align-items: center;">
                <input class="fst-italic" v-model="nickName" style="border: none; width: 8rem; margin-right: 5px">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square-fill" viewBox="0 0 16 16" @click="updateBlogInfo">
                  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm10.03 4.97a.75.75 0 0 1 .011 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.75.75 0 0 1 1.08-.022z"/>
                </svg>
              </h4>
            </div>
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
        </div>
    </div>
    <div class="card">
        <todayWeather/>
    </div>
</template>

<script>
import todayWeather from "@/components/TodayWeather.vue"
import {useStore} from "vuex";
import {computed, onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import api from "@/axios";
import {alertEvent} from "@/composables/alertEvent";
export default {
    props : {
        username: {
            type: String,
            required: true
        }
    },
    components : {
        todayWeather
    },
  setup(){
  //setup(props){
    const route = useRoute();
      const blogRoute = route.params.id;
      const profileImg = ref('');
      const nickName = ref('');
      const updateBlog = ref(false);
      const oldNick = ref(null);
      const menu = ref(null);
      const menuCnt = ref(0);
      const newMenuCnt = ref(0);

      onMounted(async () => {
        try {
          await infoBlog();
          await menuLoad();
        } catch (e) {
          setTimeAlert(true);
          setMessage("에러가 발생하였습니다.");
        }
      });

      const store = useStore();
      const {setTimeAlert, setMessage} = alertEvent(store);

      // 블로그 셋팅
      const setBlog = () => {
        updateBlog.value = true;
      }

      // 메뉴 읽어오기
      const menuLoad = async () => {
        const username = route.params.id;
        const response = await api.post('/api/selectMenu', { username : username });
        menu.value = response.data.result;
        menuCnt.value = response.data.result.length;
        newMenuCnt.value = response.data.result.length;
      }

      // 업데이트 알림
      const updateAlert = computed(() => {
        if (updateBlog.value){
          setTimeAlert(true);
          setMessage("v 버튼을 누르면 변경사항이 저장됩니다.");
        }
        return updateBlog.value
      })

      // 블로그 정보 불러오기
      const infoBlog = async () => {
        const response = await api.post('/api/blogInfo/'+blogRoute);
        profileImg.value = 'http://localhost:8081/img/'+response.data?.result?.image;
        nickName.value = response.data?.result?.nickname;
        oldNick.value = nickName.value; // 추가된 코드
      }

      const uploadProfile = () => {
        console.log("파일 업로드");
      }

      //
      const updateBlogInfo = async () => {
          updateBlog.value = true;
          console.log(newMenuCnt.value)
          console.log(menuCnt.value)
          if(oldNick.value !== nickName.value || newMenuCnt.value !== menuCnt.value){ // 값이 변경되었는지 확인
            //유저정보 가져오기
            const responseUser = await userInfo();
            // 닉네임 변경
            const result = await updateNickname(responseUser);
            console.log(result);
            setTimeAlert(true);
            setMessage("수정사항이 변경 되었습니다.");
            updateBlog.value = false;
            await infoBlog();
          }
      }

      // 닉네임 변경
      const updateNickname = async (user) => {
        return await api.post('api/updateNick', {id : user, nickname: nickName.value});
      }

      // 유저 정보 불러오기
      const userInfo = async () => {
        const responseUser = await api.post('api/userInfo/'+blogRoute);
        return responseUser.userId = responseUser.data.result;
      }


      const deleteMenu = (menuObject) => {
        if (newMenuCnt.value <= 1) {
          setTimeAlert(true);
          setMessage("메뉴 하나 이상은 필수 입니다.");
        } else {
          newMenuCnt.value--;
          menuObject.hidden = true;
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

      const updateStatus = computed(() => store.state?.user?.username);
      return {
        updateStatus, blogRoute, profileImg, nickName, setBlog, updateBlog,
        uploadProfile, updateBlogInfo, setTimeAlert, setMessage, menu, menuCnt, updateAlert, menuLoad,
        updateMenu, plusMenu, deleteMenu
      }
  }
}
</script>

<style>

</style>