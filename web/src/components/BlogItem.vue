<template>
  <div class="container">
    <!-- title -->
    <div class="title" v-text="title">
        {{title}}
    </div>
    <!-- content -->
    <div class="item-content">
        <!-- img -->
        <div class="img">
            <img src="../assets/images/zzy.jpg" alt="" srcset="">
        </div>
        <!-- intro-container -->
        <div class="intro-container">
            <!-- intro -->
            <div class="intro">
                <span v-text="intro">
                </span>
            </div>
            <!-- like comm auther -->
            <div class="like-container">
                <span></span>
            </div>
        </div>
    </div>

  </div>
</template>

<script>
export default {
    name:'BlogItem',
    props:['title','intro'],
    data(){
        return {

        }
    }

}
</script>

<style scoped>
.container {
    width: 100%;
    height: 160px;
    border-bottom: 1px solid gray;
    padding-top: 20px;
    padding-left: 10px;
    padding-right: 5px;
}
.title {
    display: inline-block;
    width: 100%;
    height: 32px;
    font-size: 24px;
    font-weight: bold;
    border-bottom: 1px solid white;
    cursor: pointer;
}

.item-content{
    border-bottom: 1px solid white;
    width:100%;
    height:100px;
    padding-top: 15px;
}
.img{
    display: inline-block;
    width: 80px;
    height: 80px;

}
.intro-container{
    display: inline-block;
    width:85%;
    height:80px;
    position: relative;
    left: 30px;
}
.intro{
    width:100%;
    height:75%;
    font-size:17px;
}
.like-container{
    width:100%;
    height:25%;
}
.like-container span{
    margin-left: 40px;
}
</style>