<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
// import axios from "axios"
export default {
  name: 'App',
  created(){
    //在页面加载时读取sessionStorage里的状态信息
    if (window.localStorage.getItem("list") ) {
        this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(window.localStorage.getItem("list"))))
    } 

    window.addEventListener("beforeunload",()=>{
          window.setItem("list",JSON.stringify(this.$store.state))
      })
    },
    
    beforeDestroy(){
      // this.$store.state.isUser = false
      localStorage.removeItem("user")
      window.localStorage.clear()
    },
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
img{
  width: 100%;
  height: 100%;
}
a{
  text-decoration: none;
}
ul,ol{
  list-style: none;
}
</style>
