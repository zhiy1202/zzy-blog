// import axios from 'axios'
import axios from '../assets/http'

const resourceModel = {
    state :{
        resourceType:[],
        resourceList:[],
        userResource:[]
    },
    getters : {
        resourceType(state){
            return state.resourceType
        },
        resourceList(state){
            return state.resourceList
        },
        userResource(state){
            return state.userResource
        }
    },
    mutations :{
        UpdateResourceType(state,value){
            state.resourceType = value
        },
        UpdateResourceList(state,value){
            state.resourceList = value
        },
        UpdateUserResource(state,value){
            state.userResource = value
        },
        RemoveUserResource(state){
            state.userResource = []
        }
    },
    actions: { 
        getResourceType(context){
            axios.get('http://localhost:81/resource/getResTypes').then(res =>{
                let result = res.data.data
                console.log('getresourcetype',result);
                context.commit('UpdateResourceType',result)
            })
        },
        getResourceList(context,rtId){
            axios.get('http://localhost:81/resource/queryRes/'+rtId).then(res =>{
                let result = res.data.data
                console.log('getResourceList',result);
                context.commit('UpdateResourceList',result)
            })
        },
        getResourceListByTitle(context,title){
            axios.get('http://localhost:81/resource/queryByTitle/'+title).then(res =>{
                let result = res.data.data
                console.log('getResourceListByTitle',result);
                context.commit('UpdateResourceList',result)
            })
        },
        getUserResource(context,rtId){
            axios.get('http://localhost:81/resource/getUserResource/'+rtId).then(res =>{
                let result = res.data.data
                console.log('getUserResource',result);
                context.commit('UpdateUserResource',result)
            })
        }
    }
}
export default resourceModel