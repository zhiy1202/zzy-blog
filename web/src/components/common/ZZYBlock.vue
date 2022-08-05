<template>
  <div class="blockStyle" :style="userstyle">
    <div class="img-intro">
        <img :src="remotaPath" alt="">
    </div>
    <div class="info-intro">
        <p>
            <a href="">下载地址</a>
        </p>
        <p>
            <span>
                资源名:
            </span>
            <span>
                {{title}}
            </span>
        </p>
        <p>
            <span>
                作者:
            </span>
            <span>
                {{auther}}
            </span>
        </p>
    </div>
  </div>
</template>

<script>
export default {
    props:['width','height','title','remotaPath','auther'],
    data(){
        return{
            userstyle:{
                'width':this.$props.width,
                'height':this.$props.height
            }
        }
    }

}
</script>

<style scoped>
.blockStyle{
    border-bottom: 1px solid skyblue;
}
.img-intro{
    width: 100%;
    height: calc(100% / 1.5);
    margin: 0 auto;
}
.info-intro p{
    font-size:20px;
    margin: 5px auto;
    text-align: center;
}
</style>