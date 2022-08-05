<template>
<!-- 评论组件 -->
  <div class="comm-item">
    <div>
        <div class="head">
            <img :src="user.headImg" alt="">
        </div>
        <div class="user-name">
            <p>{{user.username}}</p>
        </div>
    </div>
    <div class="comm-content">
        <p>
            {{content}}
        </p>
    </div>
        
    </div>
</template>

<script>
import {mapGetters} from "vuex"
export default {
    name:'CommItem',
    props:['userId','content'],
    computed:{
        ...mapGetters(['userInfo'])
    },
    data(){
        return {
            user:{}
        }
    },
    created(){
        console.log('commuserId',this.userId);
        let that = this
        this.$axios.get("/user/info/"+this.userId).then(res => {
            console.log(res.data.data);
            that.user = res.data.data
        })
    }
}
</script>

<style scoped>
.head{
    display:inline-block;
    width: 50px;
    height: 50px;
}
.head img{
    border-radius: 50px;
}
.user-name{
    display: inline-block;
    margin-left: 20px;
    font-size:20px;
    color: slateblue;
}
.comm-content{
    margin-top:10px;
    font-size:16px;
}
</style>