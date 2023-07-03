<template>
    <div v-if="noPost">
      <div class="mb-3 p-4 card">
        <div class="card-body text-center">
          작성된 글이 없습니다.
        </div>
      </div>
    </div>
    <div v-else>
      <div class="mb-3 accordion" id="accordionExample">
        <div class="accordion-item">
          <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              글 목록 보기 ({{postCnt}})
            </button>
          </h2>
          <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <table class="table table-hover">
                <tbody>
                <tr v-for="(listObject) in list" :key="listObject.id">
                  <td class="text-start"><a href="javascript:void(0)" @click="clickEvent(listObject.id)">{{listObject.subject}}</a></td>
                  <td class="text-end" style="color: gray">{{ new dayjs(listObject.writeDate).format('YYYY.MM.DD') }}</td>
                </tr>
                </tbody>
              </table>
              <nav aria-label="Page navigation example" v-if="postCnt > 10 ">
                <ul class="pagination justify-content-center">
                  <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                  <li class="page-item" v-for="pageNumber in pageNation" :key="pageNumber">
                    <a class="page-link" @click="getPostList(pageNumber)">{{ pageNumber + 1 }}</a>
                  </li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
              <div v-else>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="mb-3 card">
          <div class="card-body">
              <ul class="list-group list-group-flush">
                  <li class="list-group-item">
                      <h5 class="mb-3 card-title text-start">{{ subject }}</h5>
                      <h6 class="mb-4 card-subtitle text-muted text-end" style="color: gray; font-size: 11px;">
                        {{ writeDate }}
                      </h6>
                  </li>
                  <li class="p-4 list-group-item text-start">
                    {{content}}
                  </li>
                  <li class="mb-3 list-group-item text-end">
                      <div class="accordion accordion-flush" id="accordionFlushExample">
                        <div class="accordion-item">
                          <h2 class="accordion-header" id="flush-headingOne">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                              댓글 수 ({{ commentCnt }})
                            </button>
                          </h2>
                          <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body" v-if="commentList?.length > 0">
                              <table class="table table-borderless" v-for="(commentObject) in commentList" :key="commentObject.id">
                                <thead>
                                <tr>
                                  <th scope="col" class="text-start">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                                      <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                    </svg>
                                    {{ commentObject.nickname }}
                                  </th>
                                  <th scope="col" class="text-end" style="font-size: 11px; color: gray">
                                    {{ dayjs(commentObject.writeDate).format('YYYY.MM.DD hh:mm:ss') }}
                                  </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                  <td class="text-start text-break" colspan="2">
                                    {{ commentObject.comment }}
                                  </td>
                                </tr>
                                </tbody>
                              </table>
                            </div>
                            <div class="accordion-body" v-else>
                              새 댓글을 작성해 보세요 :)
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <li class="mb-0 list-group-item text-end">
                      <p class="text-start">
                        <b>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                          <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                        </svg>
                        {{ nickname }}
                        </b>
                      </p>
                        <textarea class="form-control mb-2" rows="3" id="message" name="message"  required data-error="Write your message" v-model="comment"></textarea>
                      <button type="button" class="btn btn-outline-primary text-end" @click="commentEvent">
                        Comment
                      </button>
                  </li>
              </ul>
          </div>
      </div>
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
          <li class="page-item" v-for="(listObject, index) in list" :key="listObject.id">
            <a class="page-link" @click="clickEvent(listObject.id)">{{ index + 1}}</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
</template>

<script>
import {computed, onMounted, reactive, ref, toRefs} from "vue";
import {useRoute} from "vue-router";
import api from "@/axios";
import dayjs from "dayjs";
import {useStore} from "vuex";
import {alertEvent} from "@/composables/alertEvent";

export default {
  methods: {dayjs},
  props: {
    postCnt: {
      type: Number,
      required: true
    }
  },
  setup(props){
    // 포스트 목록
    const listPost = reactive({
      menuId : null,
      userId : null
    });

    // 댓글 정보
    const comment = reactive({
      postId: null,
      userId: null,
      comment: ''
    });

    // 댓글 리스트
    const commentData = reactive({
      list: []
    });

    const route = useRoute();
    const store = useStore();
    const list = ref(null);
    const content = ref('');
    const subject = ref('');
    const writeDate = ref('');
    const noPost = ref(true);
    const applyPost = ref(null);
    const username = route.params.id;
    const {setTimeAlert, setMessage} = alertEvent(store);
    const pageCntVal = ref(props.postCnt);
    const pageNation = computed(() => pageEvent());

    // 댓글 감지 ( 리스트, 갯수 )
    const commentList = computed(() => commentData.list);
    const commentCnt = computed(() => commentData.list.length);

    onMounted(async () => {
      // 메뉴 정보 가져오기
      // TODO: 부모 blogMain 으로 옮기기
      const responseMenu = await api.post('/api/selectMenu', { username : username });
      listPost.menuId = responseMenu.data.result[0].id;

      // 유저 정보
      // TODO: 부모 blogMain 으로 옮기기
      const responseUser = await api.post('api/userInfo/'+username)
      listPost.userId = responseUser.data.result;

      // 포스트 리스트
      await getPostList(0);

    });

    // 포스트리스트
    const getPostList = async(postId) => {
      const responseList = await api.post('/api/post/postList/'+ postId, listPost);
      list.value = responseList.data.result;
      if (list.value.length > 0){
        noPost.value = false;
        await postInfo(responseList.data.result[0].id);
      }
    }

    // 닉네임 감지
    const nickname = computed(() => {
      if (store.state?.user?.nickname != undefined) {
        return store.state.user.nickname;
      }
      return "로그인을 해주세요.";
    });

    // 포스트 페이지 네이션 클릭 이벤트
    const clickEvent = async (id) => {
      await postInfo(id);
    }

    // 페이지네이션
    const pageEvent = () => {
      const totalPages = Math.ceil(pageCntVal.value + 10 / 10);
      const pages = [];
      for (let i = 0; i <= totalPages; i++) {
        pages.push(i);
      }
      return pages;
    };


    // 포스트 읽어오기
    const postInfo = async (id) => {
      try {
        const response = await api.post('/api/post/'+ id);
        subject.value = response.data.result.subject;
        content.value = response.data.result.content;
        writeDate.value = dayjs(response.data.result.writeDate).format('YYYY.MM.DD hh:mm:ss');
        applyPost.value = id;
        comment.comment = '';
        await commentInfo();
      } catch (error) {
        console.error("Error loading post info: ", error);
      }
    }

    // 댓글 정보 가져오기
    const commentInfo = async () => {
      commentData.list = []
      const responseComment = await api.post('/api/comment/' + applyPost.value)
      if(responseComment.data.result.length > 0){
        commentData.list = responseComment.data.result;
        commentCnt.value = commentList.value.length;
      }
    }

    // 코멘트 등록하기
    const commentEvent = async () => {
      if (store.state?.user === null || applyPost.value === null) {
        setTimeAlert(true);
        setMessage("로그인을 먼저 해주세요")
      } else {
        try {
          comment.userId = store.state?.user?.id;
          comment.postId = applyPost.value;
          const response = await api.post('/api/comment/write', comment);
          console.log(response);
          if (response.data.status == "200"){
            await commentInfo();
          } else {
            console.error("Error posting comment: ", response.data.message);
          }
        } catch (error) {
          console.error("Error posting comment: ", error);
        }
      }
    }

    return{
      ...toRefs(listPost), ...toRefs(comment),...toRefs(commentData),
      list, clickEvent,subject, content, writeDate, noPost, postInfo, commentEvent,
      nickname, setTimeAlert, setMessage, commentList, commentInfo, commentCnt, pageNation,
      username, getPostList
    }
  }
}
</script>

<style>
</style>