// import axios from 'axios'
import axios from '../assets/http'

const blogModel = {
    state: {
        blogList:[],
        blogDetail:{},
        blogInfo:{},
        blogComm:[]
    },
    getters:{
        blogList(state){
            return state.blogList
        },
        blogDetail(state){
            return state.blogDetail
        },
        blogInfo(state){
            return state.blogInfo
        },
        blogComm(state){
            return state.blogComm
        },
        
    },
    mutations: {
        RemoveBlogList(state){
          state.blogList = []
        },
        UpdateBlogList(state,value){
            state.blogList.push(...value)
        },
        UpdateBlogDetail(state,value){
            state.blogDetail = value
        },
        UpdateBlogInfo(state,value){
            state.blogInfo = value
        },
        UpdateBlogComm(state,value){
            state.blogComm = value
        },
        
    },
    actions: {
        updateBlogList(context,pageIndex){
            axios({
              method: 'get',
              url: 'http://localhost:81/blog/queryBlog/'+pageIndex
            }).then(response => {
              let result = response.data.data
              console.log('UpdateBlogList',result);
              context.commit('UpdateBlogList',result)
            })
        },
        updateBlogDetail(context,blogId){
            axios({
              method: 'get',
              url: `http://localhost:81/blog/blogDetail/${blogId}`
            }).then(response => {
              let result = response.data.data
              //获取用户信息
              context.dispatch('updateUserInfo',result.userId)
              //获取用户目录
              context.dispatch('updateUserDir',result.userId)
              context.commit('UpdateBlogDetail',result)
            })
        },
        updateBlogInfo(context,blogId){
            axios({
              method: 'get',
              url: `http://localhost:81/blog/getBlogInfo/${blogId}`
            }).then(response => {
              let result = response.data.data
              console.log(result);
              context.commit('UpdateBlogInfo',result)
            })
        },
        updateBlogComm(context,blogId){
            axios({
              method: 'get',
              url: `http://localhost:81/blog/queryComm/${blogId}`
            }).then(response => {
              let result = response.data.data
              console.log('blogcomm',result);
              context.commit('UpdateBlogComm',result)
            })
        },
        updateBlogListByTypeId(context,param){
            axios({
              method: 'get',
              url: `http://localhost:81/blog/queryType/${param.typeId}/${param.pageIndex}`
            }).then(response => {
              let result = response.data.data
              console.log('updateBlogListByTypeId',result);
              context.commit('UpdateBlogList',result)
            })
        },
        updateBlogListByDirId(context,param){
            axios({
              method: 'get',
              url: `http://localhost:81/blog/queryDir/${param.dirId}/${param.pageIndex}`
            }).then(response => {
              let result = response.data.data
              console.log('updateBlogListByDirId',result);
              context.commit('UpdateBlogList',result)
            })
        },
    }
}
export default blogModel