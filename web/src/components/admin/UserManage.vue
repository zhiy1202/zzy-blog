<template>
  <div>
    <el-table
    :data="userList"
    border
    style="width: 100%">
      <el-table-column
        prop="username"
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column
        fixed
        prop="email"
        label="邮箱"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="150">
      </el-table-column>
      <el-table-column
        prop="registerTime"
        label="注册时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="auth"
        label="权限"
        width="120">
        <template slot-scope="scope">
           <el-select v-model="scope.row.auth" placeholder="请选择">
            <el-option
              v-for="item in authOptions"
              :key="item.index"
              :label="item.intro"
              :value="item.uaId">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态"
        width="300">
        <template slot-scope="scope">
           <el-select v-model="scope.row.state" placeholder="请选择">
            <el-option
              v-for="item in stateOptions"
              :key="item.index"
              :label="item.stateName"
              :value="item.stateId">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">提交</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return{
      userList:[],
      authOptions:[],
      stateOptions:[],
      userForm:{
        auth:0
      }
    }
  },
  methods: {
    //提交修改
    handleClick(row){
      let userId = row.userId
      //修改权限
      this.$axios.put(`/user/admin/updateAuth/${userId}?auth=${row.auth}`).then(resp=>{
        console.log(resp.data);
      })
      //修改状态
      this.$axios.put(`/user/admin/updateState/${userId}?state=${row.state}`).then(resp=>{
        console.log(resp.data);
      })
    }
  },
  created(){
    let that = this
    //获取所有用户
    this.$axios.get(`/user/allUser`).then(res =>{
      let result = res.data.data
      that.userList = result
    })
    //获取权限
    this.$axios.get(`/user/allAuth`).then(rep =>{
      let result = rep.data.data
      that.authOptions = result
    })
    //获取所有状态
    this.$axios.get(`/user/admin/allState`).then(rep =>{
      let result = rep.data.data
      that.stateOptions = result
    })
  }

}
</script>

<style scoped>

</style>