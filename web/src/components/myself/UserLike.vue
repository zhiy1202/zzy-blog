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
        <span style="margin-left: 10px">{{ scope.row.p3.createTime }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="文章名"
      width="180">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>简介 : {{ scope.row.p2.intro }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.p2.title }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column
      label="作者"
      width="180">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>住址: {{ scope.row.p1.address }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.p1.username }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column
      label="简介"
      width="180">
      <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.p2.intro }}</el-tag>
          </div>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">前往</el-button>
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
import{mapActions} from 'vuex'
export default {
    created(){
      let that = this
      this.$axios.get("/user/queryLike").then(function(response) {
        console.log(response.data.data);
        that.tableData = response.data.data
      })
    },
    data() {
      return {
        tableData: []
      }
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
        //获取文章id 查询内容 更新vuex blogDetail
        let userId = row.p1.userId
        let blogId = row.p2.blogId
        console.log(userId,blogId);
        this.$router.push('/blog/'+blogId)
        //更新组件内容
        this.updateBlogDetail(blogId)
        this.updateBlogComm(blogId)
      },
      handleDelete(index, row) {
        console.log(index, row);
        let likeId = row.p3.likeId
        let that = this
        //获取likeId 删除userLike
        this.$axios.delete('/user/delLike/'+likeId).then(function(res){
          console.log(res);
          let result = res.data.data
          if (result) {
            alert("取消收藏成功")
            that.pushItem(likeId)
          }
        })
      },
      pushItem(index){
        console.log(index);
        this.tableData.forEach((item)=>{
          if (item.p3.likeId == index) {
            console.log(item);
            let index = this.tableData.indexOf(item)
            this.tableData.splice(index,1);
          }
        })
      },
      ...mapActions(['updateBlogDetail','updateBlogComm'])
    }
}
</script>

<style scoped>

</style>