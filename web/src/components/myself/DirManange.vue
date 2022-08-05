<template>
  <div>
    <div class="dir-create">
        <span>新建目录</span>
        <form action="" method="post">
            <div class="input-bar">
                <el-input v-model="input" placeholder="请输入目录名"></el-input>
            </div>
            <el-button type="primary" plain @click="createDir">创建</el-button>
        </form>
    </div>
    <div>
        <span>我的目录</span>
        <div>
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                label="日期"
                width="220">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                </template>
                </el-table-column>
                <el-table-column
                label="目录名"
                width="180">
                <template slot-scope="scope">
                    <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.dirName }}</el-tag>
                    </div>
                </template>
                </el-table-column>
                <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">查看文章</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    created(){
        let that = this
        this.$axios.get("/user/queryDirs").then(response =>{
            console.log(response);
            let result = response.data.data
            that.tableData = result
        })
    },

    data() {
        return {
            input: '',
            tableData: []
        }
    },

    methods: {
        createDir(){
            let dirName = this.input
            let formData = new FormData()
            formData.append('dirName', dirName)
            this.$axios.post("/user/addDir",formData).then(res => {
                console.log(res);
                location.reload()
            })
        },
        handleEdit(index,row){
            console.log(index);
            console.log(row);
            let userId = row.userId
            let dirId = row.dirId
            console.log(userId, dirId);
            this.updateBlogListByDirId({dirId: dirId,pageIndex:1})
            this.updateUserInfo(userId)
            this.updateUserDir(userId)
            this.$router.push(`/userBlog/${dirId}/1`)
        },
        handleDelete(index, row) {
            console.log(index);
            let dirId = row.dirId
            let that =this
            //通过dirId 删除目录
            this.$axios.delete('/user/delDir/'+dirId).then(res=>{
                console.log(res);
                let result = res.data.data
                if(result){
                    alert('目录删除成功')
                    that.pushItem(dirId)
                }
            })
        },
        pushItem(index){
        console.log(index);
        this.tableData.forEach((item)=>{
          if (item.dirId == index) {
            console.log(item);
            let index = this.tableData.indexOf(item)
            this.tableData.splice(index,1);
          }
        })
        },
        ...mapActions(['updateBlogListByDirId','updateUserInfo','updateUserDir'])
    }
}
</script>

<style scoped>
.input-bar {
    width: 50%;
    display: inline-block;
}
.dir-create{
    margin: 0 auto;
    text-align: center;
    font-size:20px;
}
</style>