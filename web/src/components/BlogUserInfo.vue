<template>
  <div class="blog-user-info">
    <div class="container">

      <div class="head">
        <router-link to="">
          <img :src="userInfo.p2.headImg" alt="">
        </router-link>
      </div>
      <div class="intro">
        <router-link to="">
          <span>{{userInfo.p2.username}}</span>
        </router-link>
        <p>{{userInfo.p2.intro}}</p>
      </div>
    </div>

    <div>
      <ul class="content-list">
        <li>
          <p>作品数量:</p>
          <p>{{userInfo.p1.blogCount}}</p>
        </li>
        <!-- <li>
          <p>访问量:</p>
          <p>{{userInfo.p1.lookCount}}</p>
        </li> -->
        <li>
          <p>点赞数:</p>
          <p>{{userInfo.p1.likeCount}}</p>
        </li>
      </ul>
    </div>

    <div>
      <ul class="user-opration">
        <li><button @click="addFriend(userInfo.p2.userId)">加好友</button></li>
      </ul>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
    name:'BlogUserInfo',
    computed:{
      ...mapGetters(['userInfo'])
    },
    methods: {
      addFriend(userId){
        console.log(userId);
        this.$axios.post('/user/sendFriend/'+userId).then(res=>{
          let result = res.data.data
          if (result) {
            alert("添加好友成功")
          }else{
            alert("以发送请求")
          }
        })
      }
    }
}
</script>

<style scoped>
.blog-user-info{
  border-right: 1px solid skyblue;
  border-bottom: 1px solid skyblue;
}
.container{
  border-bottom: 1px solid skyblue;
}
.head{
  width: 50px;
  height: 50px;
  border-radius: 40px;
  display: inline-block;
}
.intro{
  padding: 20px;
  display: inline-block;
}
.intro span{
  font-size:24px;
  font-weight:bold;
}
.head img{
  border-radius: 40px;
}

.content-list{
  display: -webkit-flex; /* Safari */
  display: flex;
  justify-content:space-around;
  margin: 15px 0;
}
.content-list li{
    text-align: center;
}
.user-opration{
  display: flex;
  justify-content:space-around;
}
button{
  width: 100px;
  height: 43px;
  background-color:skyblue;
  outline-style: auto;
  outline-color: skyblue;
}
</style>