import Vue from 'vue'
import VueX from 'vuex'
Vue.use(VueX)
import blogModel from './blogModel'
import userModel from './userModel'
import resourceModel from './resourceModel'
import adminModel from './adminModel'

const store =new VueX.Store({
  modules:{
    blog:blogModel,
    user:userModel,
    resource:resourceModel,
    admin:adminModel
  }
})

export default store