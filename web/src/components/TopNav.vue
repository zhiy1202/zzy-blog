<template>
  <div class="nav-container">
    <!-- 头像 网站名 -->
    <div class="top-left">
        <div class="head">
            <img src="../assets/images/zzy.jpg" alt="" srcset="">
        </div>
        <div class="info">
          <p>
            ZZY
          </p>
          <span>
            资源信息网
          </span>
        </div>
    </div>
    <div class="center-flex">
      <!-- opration -->
      <div class="top-opration">
        <ul>
          <li>
            <router-link to="/">博客</router-link>
          </li>
          <li>
            <router-link to="/resource">资源</router-link>
          </li>
          <li>
            <router-link to="/myself">我的</router-link>
          </li>
        </ul>
      </div>
      <!-- search -->
      <div class="search">
        <form action="" method="get">
          <input type="text" name="word" id="" placeholder="请输入查找内容">
          <button type="sumbit">搜索</button>
        </form>
      </div>
    </div>
    
    <!-- login exit -->
    <div class="top-right">
      <span>
        <router-link to="/welcome">登录/注册</router-link>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TopNav',
}
</script>

<style scoped>
.nav-container{
    width:60%;
    height: 100%;
    margin: 0 auto;
    display: flex;
}
.top-left{
  /* display: inline-block; */
  /* width:180px; */
  flex: 3;
  height:100%;
  overflow: hidden;
}
.head{
    display: inline-block;
    width: 70px;
    height: 70px;
    position: relative;
    top: 12px;
    border-radius: 70px;
}
.head img{
  border-radius: 70px;
}
.info{
  display: inline-block;
  width:100px;
  height:100px;
  text-align: center;
  /* position: relative;
  top: 10px; */
}
.center-flex{
  /* display: inline-block; */
  display:flex;
  flex: 8;
  width: 600px;
  height: 100%;
}
.top-opration{
  flex:1;
  height:100%;
}
.top-opration ul{
  display: flex;
  height:100%;
  flex-direction: row;
  align-items: center;
}
.top-opration ul li{
  flex:1;
  height: 100%;
  font-size:18px;
  text-align: center;
  line-height: 100px;
  cursor: pointer;
  transition: all 0.7s;
}
.top-opration ul li:hover{
  background-color: white;
}
.search{
  flex:1;
  height:100%;
}
.top-right{
  flex:2;
  height: 100%;
  text-align: center;
  font-size:20px;
  display:flex;
  flex-direction: column;
  justify-content: center;
  transition: all 0.7s;
}
.top-right:hover{
  background-color:white;
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
    border: 1px solid white;
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