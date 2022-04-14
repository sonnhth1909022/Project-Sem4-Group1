import { createApp } from 'vue'
import App from './App.vue'
import { FontAwesomeIcon } from './plugins/font-awesome'
import router from "./router/router";
import store from "./store";
import '@fortawesome/fontawesome-free/js/all'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
createApp(App).use(router).use(store).component("font-awesome-icon", FontAwesomeIcon).mount('#app')
