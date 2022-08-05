<template>
<!-- 博客详情页 左侧路由left -->
  <div class="user-detail">
    <!-- 用户介绍 -->
    <div>
        <BlogUserInfo></BlogUserInfo>
    </div>
    <!-- 用户目录 -->
    <div>
        <BlogUserType :title="title" :data-list="userDir"></BlogUserType>
    </div>
  </div>

  
</template>

<script>
import {mapGetters} from 'vuex'
import BlogUserInfo from './BlogUserInfo.vue'
import BlogUserType from './BlogUserType.vue'
export default {
    name:'UserDetail',
    components:{
        BlogUserInfo,
        BlogUserType
    },
    computed:{
        ...mapGetters(['userDir'])
    },
    data(){
        return {
            title: '用户笔记',
        }
    },
    
}
</script>

<style scoped>
.user-detail div{
    margin-top: 50px;
}
</style>