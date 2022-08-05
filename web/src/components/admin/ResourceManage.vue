<template>
  <div>
    <el-table
    :data="resourceList"
    border
    max-height="1000px"
    style="width: 100%">
      <el-table-column
        prop="title"
        label="资源名"
        min-width="25%">
      </el-table-column>
      <el-table-column
        prop="remotePath"
        label="地址"
        width="180"
        min-width="40%">
        <template slot-scope="scope">
          <a :href="scope.row.remotePath" target="_blank">下载地址</a>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
         min-width="25%">
      </el-table-column>
      <el-table-column
        label="操作"
         min-width="10%">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return{
      resourceList:[]
    }
  },
  methods: {
    handleClick(row){
      let resourceId = row.resourceId
      console.log(resourceId);
      this.$axios.delete(`/resource/admin/delResource/${resourceId}`).then(resp=>{
        if (resp.data.data) {
          alert('删除成功')
          location.reload()
        }
      })
    }
  },
  created() {
    let that = this
    this.$axios.get(`/resource/admin/getAllResource`).then((res) => {
      let result = res.data.data
      console.log(result);
      that.resourceList = result
    })
  }

}
</script>

<style>

</style>