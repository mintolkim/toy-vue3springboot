import {createRouter, createWebHistory} from "vue-router";
import home from "@/components/blogHome.vue"
import blogMain from "@/components/blogMain.vue";
import signIn from "@/components/SignIn.vue";
import signUp from "@/components/SignUp.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path : '/',
            name : 'home',
            component : home
        },
        {
            path : '/blog',
            name : 'blog',
            component : blogMain
        },
        {
            path : '/signin',
            name : 'signIn',
            component : signIn
        },
        {
            path : '/signup',
            name : 'signUp',
            component : signUp
        },
        {
            path : '/:id',
            name : 'myBlog',
            component : blogMain
        }
    ]
})

export default router;