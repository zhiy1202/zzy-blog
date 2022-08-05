<template>
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
        label="文章名"
        width="180">
        <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.title }}</el-tag>
            </div>
        </template>
        </el-table-column>
        <el-table-column
        label="简介"
        width="250">
        <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.intro }}</el-tag>
            </div>
        </template>
        </el-table-column>
        <el-table-column label="操作">
        <template slot-scope="scope">
            <el-button
            size="mini"
            @click="handleUpdate(scope.$index, scope.row)">修改</el-button>
            <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    created(){
      let that = this
      this.$axios.get("/blog/allBlogsByUserId").then(res=>{
        let result = res.data.data
        console.log(result);
        that.tableData = result
      })
    },
    data() {
      return {
        tableData: []
      }
    },
    methods: {
      handleUpdate(index, row) {
        console.log(index,row);
        let blogId = row.blogId
        console.log(blogId);
        this.$router.push('/updateBlog/'+blogId)
        this.updateBlogDetail(blogId)
      },
      handleDelete(index, row) {
        console.log(index,row);
        let blogId = row.blogId
        let that = this
        this.$axios.delete('/blog/delBlog/'+blogId).then(res =>{
          let result = res.data.data
          if (result) {
            alert('删除记录成功')
            that.pushItem(blogId)
          }
        })
      },
      pushItem(index){
        console.log(index);
        this.tableData.forEach((item)=>{
          if (item.blogId == index) {
            console.log(item);
            let index = this.tableData.indexOf(item)
            this.tableData.splice(index,1);
          }
        })
        },
      ...mapActions(['updateBlogDetail'])
    }
}
</script>

<style scoped>

</style>