import {useStore} from "vuex";
import {computed} from "vue";

export const alertEvent = () => {
    const store = useStore();
    const showModal = computed(()=> store.state.showModal);
    const showAlert = computed(()=> store.state.showAlert);
    const message = computed(()=> store.state.message);
    const result = computed(()=> store.state.result);

    const toggleModal = (value) => {
        store.commit('toggleModal', value);
    }
    const toggleAlert = (value) => {
        store.commit('toggleAlert', value);
    }
    const setMessage = (value) => {
        store.commit('setMessage', value);
    }
    const setResult = (value) => {
        store.commit('setResult', value);
    }

    const resetState = () => {
        store.commit('resetState');
    }

    const setTimeAlert = (value) => {
        toggleAlert(value);
        setTimeout(() => {
            toggleAlert(false);
        }, 1000);
    }

    return {
        showModal, showAlert, message, result,
        toggleModal, toggleAlert, setMessage, setResult, resetState, setTimeAlert
    }

}