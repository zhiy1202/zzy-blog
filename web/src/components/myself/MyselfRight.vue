<template>
  <div>
    <h2>好友列表</h2>
    <p>
        <el-button @click="openChat" type="warning" size="small">进入聊天室</el-button>
    </p>
    <el-table
      :data="friendList"
      :height="400"
      style="width: 100%">
      <el-table-column
        prop="p1.username"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop=""
        label="操作">
        <template slot-scope="scope">
        <!-- <template> -->
        <el-button @click="queryUserBlog(scope.row)" type="text" size="small">查看主页</el-button>
        <el-button @click="handlerDel(scope.row)" type="text" size="small">删除好友</el-button>
      </template>
      </el-table-column>
    </el-table>
    <h2>好友申请</h2>
    <el-table
      :data="allAgreeFriend"
      :height="400"
      style="width: 100%">
      <el-table-column
        prop="p2.username"
        label="申请人"
        width="180">
      </el-table-column>
      <el-table-column
        prop="p1"
        label="操作">
        <template slot-scope="scope">
        <!-- <template> -->
        <el-button @click="agree(scope.row)" type="text" size="small">同意</el-button>
        <el-button @click="reject(scope.row)" type="text" size="small">拒绝</el-button>
      </template>
      </el-table-column>
    </el-table>
    <div class="chat-room" v-show="chatShow" >
        <el-button @click="closeChat" type="warning" size="small">关闭聊天室</el-button>
        <lemon-imui 
        ref="IMUI" 
        :user="this.user"
        @pull-messages='handlePullMessages'
        @send="handleSend"  />
    </div>
  </div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
export default {
    created(){
        let that = this
        //得到用户信息
        this.$axios.get("/user/getself").then((rep)=>{
                let result = rep.data.data
                that.userId = result.userId
                that.user.id = result.userId
                that.user.displayName = result.username
                that.user.avatar = result.headImg

                //更新好友列表
                this.updateFriendList(result.userId)

                //websocket 生成
                this.initWebSocket(result.userId)
            }
        )
        this.$axios.get("/user/getAllAgreeFriend").then(res => {
            let result = res.data.data
            that.allAgreeFriend = result
        }) 
    },
    async mounted(){
        const { IMUI } = this.$refs;
        //获取所有好友
        let that = this
        await this.$axios.get(`/chat/allFriend`).then(res=>{
            let result = res.data.data
            that.friends = result
        })
        //生成用户列表
        const friendNav = []
        for (const item of this.friends) {
            let contact = {
                id: item.p1.userId,
                displayName: item.p1.username,
                avatar:item.p1.headImg,
                index:"L",
                unread:0,
                lastContent: IMUI.lastContentRender({
                    type: 'text',
                    content: item.p2.content
                }),
                lastSendTime:item.p2.createTime
            }
            friendNav.push(contact)
        }
        this.friendNav = friendNav
        IMUI.initContacts(friendNav);
    },
    destroyed(){
            this.websock.onclose = this.websocketclose()
    },
    computed:{
        ...mapGetters(['friendList','userInfo'])
    },
    data(){
        return {
            chatShow: false,
            userId:0,
            user:{
                id:0,
                displayName:'',
                avatar:'',
            },
            allAgreeFriend:[],
            friends:[],
            messageList:[],
            friendNav:[],
            websock: null,
        }
    },
    methods:{
        queryUserBlog(row){
            console.log(row);
            let userId = row.p1.userId
            console.log(userId);
            // 跳转路由('/userBlog/2/1')
            this.$router.push(`/lookUser/${userId}`)
            this.updateUserInfo(userId)
            this.updateUserDir(userId)
            //todo 新建用户详情页面
        },
        handlerDel(row){
            //todo 后端修改 返回ufId 进行删除
            let ufId = row.p2.ufId
            console.log(ufId);
            this.$axios.delete(`/user/del/${ufId}`).then(rep =>{
                let result = rep.data.data
                if (result) {
                    alert('删除好友成功')
                    location.reload()
                }
            })
        },
        agree(row){
            let afId = row.p1.afId
            this.$axios.delete(`/user/addFriend/${afId}`).then(rep =>{
                let result = rep.data.data
                console.log(result);
                if (result) {
                    location.reload()
                }
            })
        },
        reject(row){
            let afId = row.p1.afId
            this.$axios.delete(`/user/delAgreeFriend/${afId}`).then(rep =>{
                let result = rep.data.data
                console.log(result);
                if (result) {
                    location.reload()
                }
        })
        },
        openChat(){
            this.chatShow = true
        },
        closeChat(){
            this.chatShow = false
        },
        async handlePullMessages(contact, next) {
            console.log(contact);
            let friendId = contact.id
            let that = this
            //获取所有消息
            await this.$axios.get(`/chat/getChat/${friendId}`).then(res =>{
                let result = res.data.data
                console.log('getChat',result);    
                that.messageList = result
            })
            const messages = []
            //生成指定消息格式
            this.messageList.forEach((item)=>{
                let sendId = item.sendId
                let sendUser = {id:0,displayName:'',avatar:''}
                let message ={}
                if (friendId === sendId) {
                    sendUser.id = contact.id
                    sendUser.displayName = contact.displayName
                    sendUser.avatar = contact.avatar
                    message = {
                        id: item.ucId,
                        status:'succeed',
                        type:'text',
                        sendTime: item.createTime,
                        content:item.content,
                        toContactId:that.user.id,
                        fromUser:sendUser
                    }
                }else{
                    sendUser = this.user
                    message = {
                        id: item.ucId,
                        status:'succeed',
                        type:'text',
                        sendTime: item.createTime,
                        content:item.content,
                        toContactId:contact.id,
                        fromUser:sendUser
                    }
                }
                messages.push(message)
            })
            next(messages,true)
        },
        handleSend(message, next) {
            //消息
            let chatMessage = {
                userId : this.userId,
                friendId : message.toContactId,
                message : message.content
            }
            let messageJson = JSON.stringify(chatMessage)
            //发送消息
            this.websocketsend(messageJson)
            //执行到next消息会停止转圈，如果接口调用失败，可以修改消息的状态 next({status:'failed'});
            next();
        },
        //webSocket
        initWebSocket(userId){
            const { IMUI } = this.$refs;
            let wsuri = `ws://localhost:7004/chat?userId=${userId}`;
            this.websock = new WebSocket(wsuri);
            this.websock.onopen = this.websocketonopen()
            this.websock.onerror = this.websocketonerror()
            console.log('websocket',this.websock);
            this.websock.onmessage = function(e){
                let obj = JSON.parse(e.data)
                //指定消息格式
                const data = {
                    id: obj.p1,
                    status: 'succeed',
                    type: 'text',
                    sendTime: obj.p5,
                    content: obj.p4,
                    toContactId: obj.p2.userId,
                    fromUser:{ 
                        //如果 id == this.user.id消息会显示在右侧，否则在左侧
                        id:obj.p2.userId,
                        displayName:obj.p2.username,
                        avatar:obj.p2.headImg
                    }
                };
                IMUI.appendMessage(data);
            }
        },
        websocketsend(Data){//数据发送
            this.websock.send(Data);
        },
        websocketonopen(){
            console.log('聊天室以开启');
        },
        websocketonerror(){//连接建立失败重连
            // this.initWebSocket();
        },
        websocketclose(){  //关闭
            console.log('聊天室退出');
        },
        ...mapActions(['updateFriendList','updateUserInfo','updateUserDir'])
    }
}
</script>

<style scoped>
.chat-room {
    position: fixed;
    top: 150px;
    right: 500px;
}
</style>