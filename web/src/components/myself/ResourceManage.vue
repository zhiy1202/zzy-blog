<template>
  <div>
    <el-table
    :data="userResource"
        style="width: 100%">
        <el-table-column
        label="日期"
        width="200">
        <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
        </el-table-column>
        <el-table-column
        label="资源名"
        width="180">
        <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.title }}</el-tag>
            </div>
        </template>
        </el-table-column>
        <el-table-column label="操作">
        <template slot-scope="scope">
            <el-button
            size="mini"
            @click="handleLook(scope.$index, scope.row)">查看</el-button>
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
import{mapGetters} from 'vuex'
export default {
    computed:{
      ...mapGetters(['userResource'])
    },
    data() {
      return {
        tableData: []
      }
    },
    methods: {
      handleLook(index, row){
        console.log(index,row);
        let remotePath = row.remotePath
        window.open(remotePath)
      },
      handleDelete(index, row){
        console.log(index,row);
        let resourceId = row.resourceId
        console.log(resourceId);
        this.$axios.delete(`/resource/delResource/${resourceId}`).then(res=>{
          let result = res.data.data
          console.log(result);
          if (result) {
            alert('资源删除成功')
            location.reload()
          }
        })
      }
    }

}
</script>

<style scoped>

</style>