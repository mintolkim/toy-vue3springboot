import {createRouter, createWebHistory} from "vue-router";
import post from "@/components/PostTEST.vue"

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path : '/post',
            name : 'post',
            component : post
        },
    ]
})

export default router;