import Vue from 'vue'
import App from './App.vue'
import router from './router'

import store from './store';

// 导入elementui 样式 组件
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI)

//导入m-chat组件
// import MChat from '@maybecode/m-chat'

//导入lemonUI聊天室组件
import LemonIMUI from 'lemon-imui';
import 'lemon-imui/dist/index.css';
Vue.use(LemonIMUI);

// import axios from 'axios'
// axios.defaults.withCredentials=true;
// let instance = axios.create({
//   baseURL:"http://localhost:81/",
//   timeout:10000
// })
//自定义axios
import axios from './assets/http.js'
Vue.prototype.$axios = axios;

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store,
  created(){
    router.beforeEach((to,from,next)=>{
      let path = to.path
      console.log(path);
      if (path === '/welcome/login' 
      || path === '/welcome/register'
      || path ==='/admin/login') {
        next()
      }else if (path.match("/admin/(.*)")){
        if (!localStorage.getItem('admin')) {
          alert('请登录')
          this.$router.push('/admin')
        }else{
          next()
        }
      }else if (path.match("/user/(.*)")) {
        let isUser = this.$store.state.isUser
        if (isUser) {
          next()
        }else {
          this.$router.push("/welcome/login")
        }
      }else if (path === '/myself' 
      || path === '/userLike' 
      || path==='/userHistory'
      || path === '/createBlog'
      || path === '/updateBlog/:blogId'
      || path === '/dirManange'
      || path === '/allBlog'
      || path === '/allComm'
      || path === '/allImg'
      || path === '/allAudio'
      || path === '/allVideo'
      ){
        if (localStorage.getItem('user')) {
          next()
        }else {
          alert('请登录')
          this.$router.push("/welcome/login")
        }
      }else{
        next()
      }
    })
  }
}).$mount('#app')
