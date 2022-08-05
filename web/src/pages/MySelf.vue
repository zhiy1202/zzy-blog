<template>
  <div>
    <div class="head">
      <!-- <img src="../assets/images/zzy.jpg" alt=""> -->
      <el-upload
        class="avatar-uploader"
        :http-request="uploadHead"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
        action=""
        width="200"
        height="200">
        <img v-if="userFrom.headImg" :src="userFrom.headImg" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </div>
    <form action="">
      <div class="userFrom">
      <!-- 用户名 -->
      <p>
        <span>最近登录时间: </span><span>{{userFrom.loginTime}}</span>
      </p>
      <p>
        <span>用户名: </span><input type="text" v-model="userFrom.username" value="zzy">
      </p>
      <!-- 邮箱 -->
      <p>
        <span>邮箱: </span><input type="text" disabled value="zhiy1202@qq.com">
      </p>
      <!-- 密码 -->
      <p>
        <span>密码: </span><input type="password" v-model="userFrom.password" value="">
      </p>
      <p>
        <span>生日: </span><input type="date" v-model="userFrom.birthday">
      </p>
      <p>
        <span>居住地: </span><input type="text" v-model="userFrom.address" value="湖南邵阳邵东">
      </p>
      <p>
        <span>简介: </span><input type="text" v-model="userFrom.intro" value="java 开放工程师">
      </p>
      <p>
        <!-- <el-button type="warning" round>重置</el-button> -->
        <el-button type="success" round @click="updateUserInfo">修改</el-button>
        
      </p>
    </div>
    </form>
  </div>
</template>

<script>
export default {
  created(){
    let that = this
    this.$axios.get("/user/getself").then(
      (rep)=>{
        let result = rep.data.data
        that.userFrom.userId = result.userId
        that.userFrom.loginTime = result.loginTime
        that.userFrom.username = result.username
        that.userFrom.password = result.password;
        that.userFrom.birthday = result.birthday
        that.userFrom.address = result.address
        that.userFrom.intro = result.intro
        that.userFrom.headImg = result.headImg
      }
    )
  },
  data(){
    return{
      userFrom:{
        userId:0,
        loginTime:'',
        username:'',
        password:'',
        birthday:'',
        address:'',
        intro:'',
        headImg:''
      }
    }
  },
  methods: {
    uploadHead(param){
      console.log(param);
      let file = param.file
      let formDate = new FormData()
      formDate.append("file",file)
      this.$axios.post("http://localhost:81/resource/uploadHead",formDate).then(res =>{
        let result = res.data.data
        if (result) {
          location.reload()
        }
      })
    },
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 4;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
    handleAvatarSuccess(res, file){
      console.log(res);
      console.log(file);
    },
    updateUserInfo(){
      console.log(this.userFrom);
      let param = new URLSearchParams()
      param.append("username",this.userFrom.username)
      param.append("password",this.userFrom.password)
      param.append("birthday",this.userFrom.birthday)
      param.append("address",this.userFrom.address)
      param.append("intro",this.userFrom.intro)
      this.$axios.put('/user/updateInfo',param).then(res =>{
        let result = res.data.data
        if (result) {
          alert('修改成功')
        }
      })
    }
  }

}
</script>

<style scoped>
.head{
  width: 200px;
  height: 200px;
  margin: 0 auto;
}
.head img{
  border-radius: 200px;
}
.userFrom{
  text-align: center;
}
span{
  font-size:24px;

}
input{
  width: 300px;
  height: 40px;
  font-size:24px;
}
p{
  margin-top: 10px;
}

</style>