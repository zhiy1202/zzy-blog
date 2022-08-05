<template>
    <div>
        <div style="text-align:center;clear:both;position:absolute;top:0;left:260px">
        </div>
        <canvas class="canvas"></canvas>
        <div class="help">login</div> 
        <div class="ui">
        <input class="ui-input" 
        type="text" 
        placeholder="请输入指令..." />
        <span class="ui-return">↵</span>
    </div>
    <div class="overlay">
        <div class="tabs">
            <div class="tabs-labels"><span class="tabs-label">登录</span><span class="tabs-label">简介</span><span class="tabs-label">作者</span></div>
            <div class="tabs-panels">
            <ul class="tabs-panel commands">
                <li class="commands-item">
                    用户名: <input type="text" v-model="admin.username" placeholder="请输入用户名"/>
                </li>
                <li class="commands-item">
                    用户名: <input type="password" v-model="admin.password" placeholder="请输入密码"/>
                </li>
                <li class="commands-item">
                    <button @click="loginAdmin">登录</button>
                </li>
            </ul>

            <div class="tabs-panel ui-details">
                <div class="ui-details-content">
                <h1>Shape Shifter</h1>
                <p>
                    An experiment by <a href="//www.kennethcachia.com" target="_blank">Kenneth Cachia</a>.<br/>
                    <a href="//fortawesome.github.io/Font-Awesome/#icons-new" target="_blank">Font Awesome</a> is being used to render all #icons.
                </p>

                <br/><p>Visit <a href="http://www.kennethcachia.com/shape-shifter/?a=#icon thumbs-up" target="_blank">Shape Shifter</a> to use icons.</p>
                </div>
            </div>

            <div class="tabs-panel ui-share">
                <div class="ui-share-content">
                <h1>Sharing</h1>
                <p>Simply add <em>?a=</em> to the current URL to share any static or animated text. Examples:</p>
                <p>
                    <a href="http://www.kennethcachia.com/shape-shifter?a=Hello" target="_blank">www.kennethcachia.com/shape-shifter?a=Hello</a><br/>
                    <a href="http://www.kennethcachia.com/shape-shifter?a=Hello|#countdown 3" target="_blank">www.kennethcachia.com/shape-shifter?a=Hello|#countdown 3</a>
                </p>
                </div>
            </div>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
export default {
    data() {
        return {
            command:'',
            admin: {
                username:'',
                password:''
            }
        }
    },
    methods: {
        loginAdmin(){
            let formDate = new FormData()
            formDate.append('username',this.admin.username)
            formDate.append('password',this.admin.password)
            console.log(this.admin.username, this.admin.password);
            this.$axios.post(`/user/admin/login`,formDate).then(res =>{
                let result = res.data.data
                if (result) {
                    // this.$store.state.isAdmin = true
                    localStorage.setItem('admin','admin')
                    alert('欢迎')
                    this.$router.push('/admin/welcome')
                    this.UpdateNavIsShow()
                }else{
                    alert('无权限')
                    this.admin.username =''
                    this.admin.password =''
                }
            })
        },
        ...mapMutations(['UpdateNavIsShow'])
    },
    mounted(){
        let script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = '/js/index.js';
        document.body.appendChild(script);
    }
}
</script>

<style scoped>
.tabs ul li{
    margin: 0 auto;
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
<style src="../../assets/admin/css/normalize.css" scoped></style>
<style src="../../assets/admin/css/style.css" scoped></style>