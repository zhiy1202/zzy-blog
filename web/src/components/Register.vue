<template>
  <div>
    <div class="title">
        <span>Register</span>
    </div>
    <div class="sumbit">
        <form action="">
            <table>
                <tr>
                    <td>
                        <input type="text" @input="usernameCheck" v-model="account.username" placeholder="请输入你的用户名...">
                    </td>
                    <td>
                        <span>{{usernameIsOk}}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" @input="emailCheck" v-model="account.email" placeholder="请输入你的邮箱...">
                    </td>
                    <td>
                        <span>{{emailIsOk}}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="password" id="" v-model="account.password" placeholder="请输入你的密码...">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" v-model="account.code" name="" id="" placeholder="请输入邮箱发送过来的验证码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button @click="register" type="sumbit">注册</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h2 @click="sendEmial" type="sumbit">发送邮件</h2>
                    </td>
                </tr>
            </table>
        </form>
    </div>
  </div>
</template>

<script>
export default {
    name: 'Register',
    data(){
        return{
            usernameIsOk:'',
            emailIsOk:'',
            account:{
                username: '',
                email:'',
                password:'',
                code:''
            }
            
        }
    },
    methods: {
        sendEmial(){
            let emailForm = new FormData();
            emailForm.append('email',this.account.email);
            this.$axios.post(`/user/sendEmail`,emailForm).then(res =>{
                let result = res.data.data
                if (result) {
                    alert('邮件发送成功')
                }
            })
        },
        register(){
            if (!this.usernameIsOk && this.emailIsOk) {
                alert('请正确填写')
                return
            }
            let formData  = new FormData();
            formData.append('username',this.account.username);
            formData.append('email',this.account.email);
            formData.append('password',this.account.password)
            formData.append('authCode',this.account.code)
            this.$axios.post('/user/register',formData).then(res =>{
                let result = res.data.data
                if (result) {
                    alert('注册成功 请登录')
                }
            })
        },
        usernameCheck(){
            let that = this
            this.$axios.get("/user/userIsExits?username="+that.account.username).then(res =>{
                console.log(res);
                let result = res.data.data
                if (result) {
                    that.usernameIsOk ='用户已存在'
                    return false
                }else{
                    that.usernameIsOk = ''
                    return true
                }
            })
        },
        emailCheck(){
            let that = this
            this.$axios.get("/user/emailIsExits?email="+that.account.email).then(res =>{
                console.log(res);
                let result = res.data.data
                if (result) {
                    that.emailIsOk ='邮箱已存在'
                    return false
                }else{
                    that.emailIsOk = ''
                    return true
                }
            })
        }
    }
}
</script>

<style scoped>
h2{
    color:white;
    cursor: pointer;
}
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
span{
    color:white;
}
</style>