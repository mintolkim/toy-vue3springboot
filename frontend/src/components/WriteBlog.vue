<template>
  <div class="text-start" @click="clickEvent()" style="color: gray; font-size: 13px;">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
      <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
    </svg>
    이전으로
  </div>
  <p></p>
    <div class="mb-3 card">
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <li class="list-group-item justify-content-end">
                  <select class="form-select w-25" v-model="selectedMenu" style="font-size: 11px">
                    <option
                        v-for="(menuObject) in menu"
                        :key="menuObject.id"
                        :value="menuObject.id"
                    >
                      {{menuObject.menuName}}
                    </option>
                  </select>
                </li>
                <li class="list-group-item">
                    <h5 class="mb-3 card-title text-start">
                        <input placeholder="Subject" class="form-control" id="subject" type="text" name="subject" required data-error="Please enter your message subject" style="border: none" v-model="subject" v-if="updateBooleanRef">
                        <input placeholder="Subject" class="form-control" id="subject" type="text" name="subject" required data-error="Please enter your message subject" style="border: none" v-model="subject" v-else>
                    </h5>
                  <div style="color: gray; font-size: 11px;" class="text-end">
                    {{ writeDate }}
                  </div>
                </li>
                <li class="p-4 list-group-item text-start">
                  <textarea class="form-control" rows="10" id="message" name="message"  required data-error="Write your message" v-model="content" v-if="updateBooleanRef"></textarea>
                  <textarea class="form-control" rows="10" id="message" name="message"  required data-error="Write your message" v-model="content" v-else></textarea>
                </li>
                <li class="mb-0 list-group-item">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                          <li>
                            &nbsp;
                            <button type="button" class="btn btn-outline-primary">
                              임시 저장
                            </button>
                          </li>
                          <li>
                            <button type="button" class="btn btn-outline-primary" @click="submitEvent">
                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                              </svg>
                              등록
                            </button>
                          </li>
                        </ul>
                    </nav>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>

import {computed, onMounted, reactive, ref, toRefs} from "vue";
import dayjs from "dayjs";
import {useRoute} from "vue-router";
import {useStore} from "vuex";
import {alertEvent} from "@/composables/alertEvent";
import api from "@/axios";

export default {
  emits: ['movePost'],
  props: {
    updateBoolean : {
      type: Boolean,
      required: false,
      default: false
    },
    postId : {
      type: Number,
      required: false,
      default: null
    }
  },
  setup(props, { emit }){
    const route = useRoute();
    const store = useStore();
    const {setTimeAlert, setMessage} = alertEvent(store);
    const menu = ref('');
    const selectedMenu = ref(null);
    const username = route.params.id;
    const visitUserStatus = computed(() => store.state?.user?.username);
    const postData = reactive({
      subject: '',
      content: '',
      menuId: '',
      userId: '',
      updateDate: ''
    });
    const updateBooleanRef = ref(props.updateBoolean);
    const postIdRef = ref(props.postId);
    // const postDataRefs = toRefs(postData);

    // 수정 그려주기
    const drawUpdate = async () => {
      const response = await api.post('/api/post/'+ postIdRef.value);
      postData.subject = response.data.subject;
      postData.content = response.data.content;
      selectedMenu.value = response.data.menuId;
      console.log(postData.subject);
      console.log(postData.content);
      console.log(selectedMenu.value);
    }

/*    watchEffect(async () => {
      // 수정 / 글쓰기 구분
      if(updateBooleanRef.value){
        console.log('efef')
      }
    });*/

    onMounted(async () => {
      try {
        const response = await api.post('/api/selectMenu', { username : username });
        menu.value = response.data.result;
        selectedMenu.value = menu.value[0].id;
        if(updateBooleanRef.value){
          await drawUpdate();
        }
      } catch (e) {
        setTimeAlert(true);
        setMessage("에러가 발생하였습니다.");
      }
    });

    // 글 등록 이벤트
    const submitEvent = async () => {
      if (typeof username === 'string' && visitUserStatus.value === username) {
        if(updateBooleanRef.value){
          // 수정
          postData.userId = store.state?.user?.id;
          postData.menuId = selectedMenu.value;
          const response = await api.post('/api/update', postData);
          console.log(response);
          emit('movePost');
          emit('postCnt');
        } else {
          // 등록
        postData.userId = store.state?.user?.id;
        postData.menuId = selectedMenu.value;
        await api.post('/api/post/write', postData);
        emit('movePost');
        emit('postCnt');
        }
      } else {
        setTimeAlert(true);
        setMessage("글 등록 권한이 없습니다.");
      }
    }
    // 글 작성 date
    const writeDate = ref(new dayjs().format('YYYY.MM.DD'));
    const clickEvent = () => {
      emit('movePost');
    }

    return{
      clickEvent, writeDate, ...toRefs(postData), submitEvent, menu, selectedMenu, updateBooleanRef, postIdRef, drawUpdate
   }
  }
}
</script>

<style>

</style>