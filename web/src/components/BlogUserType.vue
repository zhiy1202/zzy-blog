<template>
    <div class="blog-user-type">
        <h2 style="margin:0 auto;border-bottom: 1px solid skyblue;">{{title}}</h2>
        <ul>
            <li v-for="(item,index) in dataList" :key="index">
                <span @click="changeBlogListByDir(item.dirId)">{{item.dirName}}</span>
            </li>
        </ul>
    </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
    name: 'BlogUserType',
    props:['title','dataList'],
    data(){
        return{
            pageIndex:1,
            dirId:0
        }
    },
    methods: {
        changeBlogListByDir(dirId){
            this.dirId = dirId
            this.$router.push(`/userBlog/${this.dirId}/${this.pageIndex}`)
            this.$store.commit('RemoveBlogList')
            this.updateBlogListByDirId({dirId:this.dirId,pageIndex:this.pageIndex})
        },
        ...mapActions(['updateBlogListByDirId'])
    }

}
</script>

<style scoped>
.blog-user-type{
    border: 1px solid skyblue;
    border-bottom: 0px solid;
}
ul li{
    height: 30px;
    margin: 15px 0;
    text-align: center;
    font-size:20px;
    border-bottom: 1px solid skyblue;
    line-height:20px;
}
ul li span:hover{
    cursor: pointer;
    border-bottom: 1px solid skyblue;
}
</style>