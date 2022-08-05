<template>
  <div>
    <h2>文章分类</h2>
    <ul>
        <!-- 5个一列 分列查询 -->
        <li class="type-row" v-for="type,index in types" :key=index>

            <div class="type-item" v-for="item in type" :key="item.typeId">
                <el-button @click="changeBlogList(item.typeId)" type="text">{{item.typeName}}</el-button>
            </div>
        </li>
    </ul>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    name:'BlogType',
    data(){
        return{
            types:[],
        }
    },
    created(){
        let that = this;
        this.$axios.get('/blog/getBlogTypes').then((resp)=>{
            let types = resp.data.data
            that.types = [];
            let ar = []
            for (let index = 0; index < types.length; index++) {
                if (ar.length == 4) {
                    that.types.push(ar)
                    ar = []
                }
                ar.push(types[index])
                if (index === types.length - 1) {
                    that.types.push(ar)
                }
            }
            console.log("that",that.types);


        })
    },
    methods: {
        changeBlogList(typeId){
            console.log(typeId);
            this.$router.push(`/typeBlog/${typeId}/`+1)  
            this.$store.commit('RemoveBlogList')
            this.updateBlogListByTypeId({typeId:typeId,pageIndex:1})
        },
        ...mapActions(['updateBlogListByTypeId'])
    }
}
</script>

<style scoped>
.type-row{
    width:100%;
    display:flex;
    justify-content:space-around;
    margin-top: 30px;
}
.type-item:hover{
    border-bottom: 1px solid skyblue;
}
</style>