<template>
    <div class="mb-3 accordion" id="accordionExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                   글 목록 보기
                </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    <table class="table">
                      <tbody>
                      <tr v-for="(listObject) in list" :key="listObject.id">
                        <td class="text-start"><a href="javascript:void(0)" @click="clickEvent(listObject.id)">{{listObject.subject}}</a></td>
                        <td class="text-end" style="color: gray">{{ new dayjs(listObject.writeDate).format('YYYY.MM.DD') }}</td>
                      </tr>
                      </tbody>
                    </table>
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
                <li class="mb-0 list-group-item">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import {onMounted, reactive, ref, toRefs} from "vue";
import {useRoute} from "vue-router";
import api from "@/axios";
import dayjs from "dayjs";

export default {
  methods: {dayjs},
  setup(){
    const listPost = reactive({
      menuId : null,
      userId : null
    });
    const route = useRoute();
    const username = route.params.id;
    const list = ref(null);
    const content = ref('');
    const subject = ref('');
    const writeDate = ref('');
    onMounted(async () => {
      const responseMenu = await api.post('/api/selectMenu', { username : username });
      listPost.menuId = responseMenu.data.result[0].id;

      const responseUser = await api.post('api/userInfo/'+username)
      listPost.userId = responseUser.data.result;

      const responseList = await api.post('/api/post/postList/'+ 0, listPost);
      list.value = responseList.data.result;
    })

    const clickEvent = async (id) => {
      const response = await api.post('/api/post/'+ id);
      subject.value = response.data.result.subject;
      content.value = response.data.result.content;
      writeDate.value = dayjs(response.data.result.writeDate).format('YYYY.MM.DD hh:mm:ss');
    }
    return{
      ...toRefs(listPost), list, clickEvent,subject, content, writeDate
    }
  }
}
</script>

<style>

</style>