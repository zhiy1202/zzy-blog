<template>
  <div>
    <div class="title">
            <span>Welcome</span>
        </div>
        <div class="sumbit">
            <!-- <form> -->
                <table>
                    <tr>
                        <td>
                            <input type="text" name="account" v-model="user.account" placeholder="请输入用户名/邮箱">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password" v-model="user.password" placeholder="请输入密码">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button @click="login">登录</button>
                        </td>
                    </tr>
                </table>
            <!-- </form> -->
        </div>
  </div>
</template>

<script>
// import axios from "axios"
export default {
    name: "Login",
    data(){
        return {
            user:{
                account:'',
                password:''
            }
        }
    },
    methods: {
        login(){
            let that = this
            let param = new URLSearchParams();
            param.append('account',this.user.account)
            param.append('password',this.user.password)
            console.log(that.user.account,that.user.password);
            this.$axios.post('/user/login', param).then((res)=>{
                let loginIsOk = res.data.data;
                console.log(loginIsOk);
                if (loginIsOk) {
                    // this.$axios.defaults.headers.common['user'] = 'user'
                    // axios.defaults.headers.common['user'] = 'user'
                    localStorage.setItem('user','user')
                    this.$store.state.isUser = true
                    this.$router.push('/')
                }
            })
        }
    }
}
</script>

<style scoped>
.title{
    width: 100%;
    text-align: center;
    font-family: 'Courier New', Courier, monospace;
    font-size:34px;
    font-weight: bold;
    color: antiquewhite;
    position: relative;
    top: 30px;
}
.sumbit{
    margin: 0 auto;
    width: 80%;
    height: 400px;
    position: relative;
    top: 60px;
}
form{
    width: 100%;
}
table{
    width: 100%;
    text-align: center;
}
input{
    width: 300px;
    height: 50px;
    border-radius: 30px;
    padding-left: 25px;
    font-size: 20px;
    display: inline-block;
    margin-top: 30px;
}
button{
    width:150px;
    height: 50px;
    margin-top: 30px;
    border-radius: 20px;
    cursor: pointer;
    transition:all 0.5s;
}
button:hover{
    background-color:gray;
    width:160px;
    height: 55px;
    border-radius:30px;
}

</style>