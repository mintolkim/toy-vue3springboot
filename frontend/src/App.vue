<template>
  <blog-main :name="name"/>
</template>

<script>
import BlogMain from "@/components/blogMain.vue";
import {ref} from "vue";
export default {
    components: {
        BlogMain
    },
    setup () {
        const name = ref('mintol');

        return{
            name
        }
    }

}
</script>

<style>

</style>
