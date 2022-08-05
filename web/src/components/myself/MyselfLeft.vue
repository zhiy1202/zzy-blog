<template>
  <div>
    <el-row class="tac"
    :style="{width: '100%'}">
        <el-col :span="12"
        :style="{width: '100%'}">
            <h1>用户管理台</h1>
            <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :router="true">

            <el-menu-item index="/myself">
                <i class="el-icon-user"></i>
                <span slot="title">个人信息</span>
            </el-menu-item>

            <el-submenu index="1">
                <template slot="title">
                <i class="el-icon-location"></i>
                <span>博客管理</span>
                </template>

                <el-menu-item-group>
                <template slot="title">记录</template>
                <el-menu-item index="/userLike">喜欢</el-menu-item>
                <el-menu-item index="/userHistory">历史</el-menu-item>
                </el-menu-item-group>

                <el-menu-item-group title="添加">
                <el-menu-item index="/createBlog">新建博客</el-menu-item>
                <el-menu-item index="/dirManange">目录管理</el-menu-item>
                </el-menu-item-group>

                <el-submenu index="1-4">
                <template slot="title">管理</template>
                <el-menu-item index="/allBlog">我的文章</el-menu-item>
                <el-menu-item index="/allComm">我的评论</el-menu-item>
                </el-submenu>
            </el-submenu>

            <el-submenu index="2">
                <template slot="title">
                <i class="el-icon-menu"></i>
                <span slot="title">资源管理</span>
                </template>
                
                <el-menu-item index="/allImg" @click="userImg">我的图片</el-menu-item>
                <el-menu-item index="/allAudio" @click="userAudio">我的音乐</el-menu-item>
                <el-menu-item index="/allVideo" @click="userVideo">我的视频</el-menu-item>
                <el-menu-item index="/allFile" @click="userFile">我的文件</el-menu-item>
            </el-submenu>

            <el-menu-item index="/uploadResource">
                <i class="el-icon-document"></i>
                <span slot="title">资源上传</span>
            </el-menu-item>

            <el-menu-item index="/exit" @click="exit">
                <i class="el-icon-switch-button"></i>
                <span slot="title">退出登录</span>
            </el-menu-item>

            </el-menu>
        </el-col>
    </el-row>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
// import axios from 'axios'
// import axios from '../../assets/http'
export default {
    methods:{
        exit(){
            this.$router.push('/welcome/login')
            // delete axios.defaults.headers.common['user']
            // delete this.$axios.defaults.headers.common['user']
            localStorage.removeItem('user');
            this.$store.state.isUser = false
        },
        userImg(){
            this.getUserResource(1)
        },
        userAudio(){
            this.getUserResource(2)
        },
        userVideo(){
            this.getUserResource(3)
        },
        userFile(){
            this.getUserResource(4)
        },
        getUserResource(rtId){
            this.$store.commit('RemoveUserResource')
            this.getUserResource(rtId);
        },
        ...mapActions(['getUserResource'])

    }
}
</script>

<style scoped>

</style>