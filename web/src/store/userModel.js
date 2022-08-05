// import axios from 'axios'
import axios from '../assets/http'

const userModel = {
    state :{
        userInfo:{},
        userDir:[],
        friendList:[]
    },
    getters:{
        userInfo(state){
            return state.userInfo
        },
        userDir(state){
            return state.userDir
        },
        friendList(state){
            return state.friendList
        },
    },
    mutations:{
        UpdateUserInfo(state,value){
            state.userInfo = value
        },
        UpdateUserDir(state,value){
            state.userDir = value
        },
        UpdateFriendList(state,value){
            state.friendList = value
        }
    },
    actions:{
        updateUserInfo(context,userId){
            axios({
                method:'get',
                url:`http://localhost:81/user/getUserInfo/${userId}`
            }).then(response => {
                let result = response.data.data
                console.log('updateUserInfo',result);
                context.commit('UpdateUserInfo',result)
            })
        },
        updateUserDir(context,userId){
            axios({
                method:'get',
                url:`http://localhost:81/user/queryDirById/${userId}`
            }).then(response => {
                let result = response.data.data
                console.log('updateUserDir',result);
                context.commit('UpdateUserDir',result)
            })
        },
        updateFriendList(context,userId){
            axios({
                method:'get',
                url:`http://localhost:81/user/getFriend/${userId}`
            }).then(response => {
                let result = response.data.data
                console.log('updateFriendList',result);
                context.commit('UpdateFriendList',result)
            })
        },
    }
}
export default userModel;