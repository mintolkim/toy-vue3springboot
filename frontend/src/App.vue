<template>
    <AlertModal v-if="showAlert" />
    <ModalComponent v-if="showModal" />
    <blog-main :name="name"/>
</template>

<script>
import BlogMain from "@/components/blogMain.vue";
import ModalComponent from "@/components/ModalComponent.vue"
import {ref} from "vue";
import AlertModal from "@/components/AlertModal.vue"
export default {
    components: {
        BlogMain,
        ModalComponent,
        AlertModal
    },
    setup () {
        const name = ref('mintol');
        const showModal = ref(false);
        const showAlert = ref(false);
        return{
            name,
            showModal,
            showAlert
        }
    }

}
</script>

<style>

</style>
