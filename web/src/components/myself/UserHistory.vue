<template>
  <div>
    <el-table
    :key="isShow"
    :data="tableData"
    style="width: 100%"
    sortable>
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
          <p>简介: {{ scope.row.p2.intro }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.p2.title }}</el-tag>
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
    created() {
      let that = this;
      this.$axios.get("/user/queryHistory").then(res => {
        console.log(res);
        let result = res.data.data
        that.tableData = result
      })
    },
    data() {
      return {
        tableData: [],
        isShow:0
      }
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
        let blogId = row.p2.blogId
        this.updateBlogDetail(blogId)
        this.updateBlogComm(blogId)
        this.$router.push('/blog/'+blogId)
      },
      handleDelete(index, row) {
        console.log(index, row);
        let htId = row.p3.htId
        let that = this
        this.$axios.delete('/user/delHistory/'+htId).then(res => {
          let result = res.data.data
          if (result) {
            alert('删除浏览记录成功')
            that.pushItem(htId)
          }
        })
      },
      pushItem(index){
        console.log(index);
        this.tableData.forEach((item)=>{
          if (item.p3.htId == index) {
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

<style>

</style>