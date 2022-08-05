<template>
  <div>
    <!-- top nav -->
    <div class="top-nav">
        <TopNav/>
    </div>
    <!-- blog-type -->
    <div class="blog-type">
        <router-view name="left"></router-view>
    </div>
<!-- content -->
    <div class="content">
        <router-view></router-view>
    </div>
    <!-- right -->
    <div class="right">
        <router-view name="right"></router-view>
    </div>

  </div>
</template>

<script>
import TopNav from '../components/TopNav.vue'

export default {
    name: 'Index',
    components:{
        TopNav,
    },
    data(){
        return {

        }
    }

}
</script>

<style>
.top-nav {
    width: 100%;
    height: 100px;
    background-color: skyblue;
    position: fixed;
    border-bottom: 2px solid white;
    z-index: 1;
}
.content {
    width: 58%;
    margin: 0 auto;
    position: relative;
    top: 110px;
    z-index: 0;
}
.blog-type {
    width: 20%;
    height: 400px;
    position: fixed;
    top: 100px;

}
.right{
    width: 20%;
    position: fixed;
    top: 120px;
    right:10px;
}

</style>