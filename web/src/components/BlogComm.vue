<template>
    <div class="blog-comm">
        <div>
            <ul>
                <li v-for="item in blogComm" :key=item.commId>
                    <CommItem
                    :userId="item.userId"
                    :content="item.content"
                    ></CommItem>
                </li>
            </ul>
        </div>
        <div class="comm-send">
            <!-- <form> -->
                <input type="text" v-model="content" name="comm" id="" placeholder="输入想说">
                <button @click="sendComm">发送</button>
            <!-- </form> -->
        </div>
    </div>
</template>

<script>
import {mapGetters , mapActions} from 'vuex'
import CommItem from '../components/CommItem.vue'
export default {
    name:'BlogComm',
    data(){
        return {
            content:''
        }
    },
    components:{
        CommItem
    },
    computed:{
        ...mapGetters(['blogComm'])
    },
    methods: {
        sendComm(){
            let blogId = this.$route.params.blogId
            let param = new URLSearchParams()
            param.append('content',this.content)
            this.$axios.post('/blog/addBlogComm/'+blogId ,param).then(res => {
                console.log(res);
                console.log('评论更新成功');
                this.updateBlogComm(blogId)
            })
            this.content = ''
        },
        ...mapActions(['updateBlogComm'])
    }
}
</script>

<style scoped>
.blog-comm{
    width: 100%;
    height: 800px;
    overflow:auto;
    /* position: fixed; */
}
.blog-comm li{
    margin-top: 20px;
    border-bottom: 1px solid skyblue;
}
.comm-send{
    position: fixed;
    bottom: 30px;
}
input{
    width: 200px;
    height: 40px;
    border-top-left-radius: 30px;
    border-bottom-left-radius: 30px;
    padding-left: 25px;
    font-size: 20px;
    display: inline-block;
    margin-top: 30px;
    outline-style: none;
    border: 1px solid skyblue;
}
button{
  width: 65px;
  height:40px;
  border-top-right-radius: 30px;
  border-bottom-right-radius: 30px;
  font-size: 20px;
  background-color:skyblue;
  cursor: pointer;
  border: 1px solid white;
  transition: all 0.2s;
}
button:hover{
  background-color:white;
  height: 44px;
}
</style>