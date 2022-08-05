<template>
  <div class="container" v-bind:style="{width:windowStyle.width+'px', height:windowStyle.height+'px',backgroundImage:windowStyle.backgroundImage}">
    <div class="loginBar">
        <div class="head">
            <img src="../assets/images/zzy.jpg"/>
        </div>
        <div class="info">
            <p>ZZY</p>
            <p>信息资源</p>
            <p>交流网</p>
        </div>
        <div class="opration">
            <div class="login">
                <span>
                    <router-link to="/welcome/login">登录</router-link>
                </span>
            </div>
            <div class="register">
                <span> 
                    <router-link to="/welcome/register">注册</router-link>
                </span>
            </div>
        </div>
        <div class="intro">
            <div class="blog">
                <span>
                    <a href="http://cnblogs.com/zhiy1202" target="_blank">个人博客</a>
                </span>
            </div>
            <div class="gitee">
                <span>
                    <a href="http://gitee.com/zhiy1202" target="_blank">个人码云</a>
                </span>
            </div>
        </div>
    </div>
    <div class="form">
        <router-view></router-view>
    </div>
  </div>
</template>

<script>
import one from '../assets/images/1.jpg';
import two from '../assets/images/2.jpg';
import three from '../assets/images/3.jpg';

export default {
    name: "Welcome",
    data(){
        return {
            windowStyle:{
                height:0,
                width:0,
                backgroundImage:`url(${one})`
            }
        }
    },
    beforeCreate(){
        console.log('创建之前');
    },
    created(){
        console.log('创建之后');
        this.windowStyle.height = window.innerHeight
        this.windowStyle.width = window.innerWidth
        console.log(this.windowStyle.height,this.windowStyle.width);

        let backgroundImg = [`url(${one})`,`url(${two})`,`url(${three})`]
        let container = document.querySelector('.container')
        console.log(container);
        let imgIndex = 0
        setInterval(() => {
            if(imgIndex == backgroundImg.length-1 ){
                imgIndex = -1
            }
            imgIndex = imgIndex + 1
            this.windowStyle.backgroundImage = backgroundImg[imgIndex]
        },2000)
    }

}
</script>

<style scoped>
.container {
    background-size: 100% 100%;   
    background-repeat: no-repeat;
    transition: all 1s;
}
.loginBar{
    width:50%;
    height:130px;
    background-color:skyblue;
    margin: 0 auto;
    border-bottom-left-radius: 50px;
    border-bottom-right-radius: 50px;
    display: flex;
}
.head{
    flex: 1;
    width: 100px;
    height: 100px;
    position: relative;
    left: 15px;
    top: 10px;
}
.info{
    flex: 1;
    font-size: 20px;
    position: relative;
    left: 25px;
    top: 25px;
}
.opration{
    flex: 4;
    font-size: 30px;
    display: flex;
    direction: inherit;
    justify-content: space-around;
    line-height:130px;
}
.intro{
    flex: 2;
    font-size:24px;
    display: flex;
    flex-direction:column;
    justify-content: space-around;
}
.form{
    width: 500px;
    height: 500px;
    background-color:black;
    margin: 40px auto;
    opacity: 70%;
    border-radius: 30px;
}

</style>