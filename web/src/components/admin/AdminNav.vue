<template>
  <div>
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
        <el-radio-button :label="false">展开</el-radio-button>
        <el-radio-button :label="true">收起</el-radio-button>
    </el-radio-group>
    <el-menu 
    default-active="1-4-1" 
    class="el-menu-vertical-demo" 
    @open="handleOpen" 
    @close="handleClose" 
    :collapse="isCollapse"
    background-color="#000000"
    text-color="#ffffff"
    :router="true">
        <el-menu-item index="/admin/adminUser">
            <i class="el-icon-user-solid"></i>
            <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/adminBlog">
            <i class="el-icon-document"></i>
            <span slot="title">博客管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/adminResourcce">
            <i class="el-icon-menu"></i>
            <span slot="title">资源管理</span>
        </el-menu-item>
        <el-menu-item index="/admin" @click="exit">
            <i class="el-icon-switch-button"></i>
            <span slot="title">退出</span>
        </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
    data() {
      return {
        isCollapse: true
      };
    },
    methods: {
      exit(){
        delete this.$axios.defaults.headers.common['admin']
        localStorage.removeItem('admin')
        this.$store.state.isAdmin = false;
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 400px;
    /* min-height: 400px; */
}
</style>