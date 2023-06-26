// store.js
import {createStore} from "vuex";

const states = {
    showModal: false,
    showAlert: false,
    message: '',
    result: null,
    user: null
};

export default createStore({
    state: {...states},
    mutations: {
        toggleModal(state, showModal) {
            state.showModal = showModal;
        },
        toggleAlert(state, showAlert) {
            state.showAlert = showAlert;
        },
        setMessage(state, message) {
            state.message = message;
        },
        setResult(state, result) {
            state.result = result;
        },
        resetState(state) {
            Object.assign(state, {...states});
        },
        setUser(state, user) {
            state.user = user;
        }
    },
    actions: {
        logIn({ commit }, user){
            commit('setUser', user)
        },
        logOut({ commit }){
            commit('setUser', null)
        }
    },
    getters: {
        isLogged: state => !!state.user
    },
});
