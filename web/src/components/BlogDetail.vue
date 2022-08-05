<template>
  <div class="blog-detail">
    <!-- 内容 -->
    <div class="title">
      <h1>{{blogDetail.title}}</h1>
    </div>
    <div class="intro">
      <ul class="intro-list">
        <li>浏览量:{{blogInfo.p1.lookCount}}</li>
        <li>收藏数量:{{blogInfo.p1.likeCount}}</li>
        <li>创建时间:{{blogDetail.createTime}}</li>
        <li>
          <el-button @click="addUserLike(blogDetail.blogId)" type="text">点赞</el-button>
        </li>
      </ul>
    </div>

    <div class="blog-content" >
      <MarkdownPreview
      :initialValue="blogDetail.content"/>
    </div>
    
  </div>
</template>

<script>
import {mapGetters , mapActions} from 'vuex'
import { MarkdownPreview } from 'vue-meditor'
export default {
    name:'BlogDetail',
    components:{
      MarkdownPreview
    },
    created(){
      // 获取路径变量
      let blogId = this.$route.params.blogId
      this.updateBlogDetail(blogId)
      this.updateBlogInfo(blogId)
      this.updateBlogComm(blogId)
      //添加历史记录
      this.$axios.post(`/user/addHistory/${blogId}`)

    },
    computed: {
      ...mapGetters(['blogDetail','blogInfo'])
    },
    methods: {
      addUserLike(blogId){
        console.log(blogId);
        //添加用户喜欢
        this.$axios.post(`/user/addLike/${blogId}`)
        //更新点赞数量
        this.$axios.put(`/blog/changeBlogLike/${blogId}`)
        //添加历史记录
      },
      ...mapActions(['updateBlogDetail','updateBlogInfo','updateBlogComm'])
    }
}
</script>

<style scoped>
.intro{
  margin: 30px auto;
  font-weight: bold;
}
.intro-list{
  display:flex;
  justify-content:space-around;
}
.blog-content{
  margin: 30px auto;
}
</style>