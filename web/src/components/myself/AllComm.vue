<template>
  <div>
    <el-collapse draggable >
      <el-collapse-item 
        v-for="item in tableData"
        :title="item.p1.title" 
        :name="item.p1.blogId" 
        :key="item.p1.blogId">
          <el-table
          :data="item.p2"
          style="width: 100%"
          max-height="250">
          <el-table-column
            fixed
            prop="createTime"
            label="日期"
            width="170">
          </el-table-column>
          <el-table-column
            prop="content"
            label="内容"
            width="120">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="120">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="deleteComm(scope.$index, scope.row)"
                type="text"
                size="small">
                移除
              </el-button>
            </template>
          </el-table-column>
          </el-table>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
export default {
  created(){
    //获取用户的所有评论
    let that = this;
    this.$axios.get("/blog/allCommByUser").then(res => {
      let result = res.data.data
      console.log('allComm',result);
      that.tableData = result;
    })
  },
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    deleteComm(index,row){
      console.log(index,row);
      let commId = row.commId
      console.log(commId);
      this.$axios.delete(`/blog/deleteCommById/${commId}`).then(res =>{
        let result = res.data.data
        if(result){
          alert("删除评论成功")
          location.reload()
        }
      })
    }
  }
}
</script>

<style scoped>

</style>