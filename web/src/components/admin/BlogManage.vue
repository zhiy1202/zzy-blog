<template>
  <div>
    <el-table
    :data="blogList"
    border
    max-height="1000px"
    style="width: 100%">
      <el-table-column
        prop="title"
        label="文章名"
        min-width="25%">
        <template slot-scope="scope">
          <el-popover trigger="click" placement="left">
            <div class="mark_bar">
              <MarkdownPreview
              
              :initialValue="scope.row.content"/>
            </div>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.title }}</el-tag>
          </div>
        </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="intro"
        label="简介"
        width="180"
        min-width="40%">
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
import { MarkdownPreview } from 'vue-meditor'
export default {
  data(){
    return {
        blogList:[],
    }
  },
  methods: {
    handleClick(row){
      console.log(row);
      let blogId = row.blogId;
      console.log(blogId);
      this.$axios.delete(`/blog/delBlog/${blogId}`).then(resp => {
        if (resp.data.data) {
          alert('删除成功')
          location.reload()
        }
      })
    }
  },
  components:{
    MarkdownPreview
  },
  created(){
    let that = this;
    this.$axios.get(`/blog/admin/getAllBlog`).then(response =>{
      let result = response.data.data
      that.blogList = result
    })
  }

}
</script>

<style scoped>
.mark_bar{
  width: 650px;
  height:800px;
  overflow:auto;
}
</style>