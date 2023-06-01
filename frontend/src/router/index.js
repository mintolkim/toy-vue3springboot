import  {createRouter, createWebHashHistory} from "vue-router";
import post from "@/components/PostTEST.vue"

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path : '/post',
            name : 'post',
            component : post
        },
    ]
})

export default router;