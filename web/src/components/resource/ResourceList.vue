<template>
  <div>
    <el-table
        :data="resourceList"
        border
        style="width: 100%">
        <el-table-column
        prop="title"
        label="标题"
        width="180">
        </el-table-column>
        <el-table-column
        prop="userId"
        label="作者"
        width="180">
        </el-table-column>
        <el-table-column
        prop="createTime"
        label="时间">
        </el-table-column>
        <el-table-column
        prop="remotePath"
        label="下载地址">
        <template slot-scope="scope">
            <a :href="scope.row.remotePath" target="_blank">下载地址</a>
        </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {mapGetters} from "vuex"
export default {
    computed:{
        ...mapGetters(['resourceList'])
    }

}
</script>

<style scoped>

</style>