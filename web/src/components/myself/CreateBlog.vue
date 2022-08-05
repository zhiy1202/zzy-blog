<template>
  <div>
    标题: <el-input v-model="title" placeholder="请输入标题"></el-input>
    简介: <el-input v-model="intro" placeholder="请输入文章简介"></el-input>
    用户目录: 
    <el-select v-model="dirId" placeholder="请选择">
        <el-option
        v-for="item in dirOptions"
        :key="item.dirId"
        :label="item.dirName"
        :value="item.dirId">
      </el-option>
    </el-select>
    系统分类:
    <el-select v-model="typeId" placeholder="请选择">
        <el-option
        v-for="item in typeOptions"
        :key="item.typeId"
        :label="item.typeName"
        :value="item.typeId">
      </el-option>
    </el-select>
    是否公开:
    <el-select v-model="privateId" placeholder="请选择">
        <el-option
        v-for="item in privateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
    
    <el-button type="primary" size="medium" @click="saveBlog">提交</el-button>
    <MarkdownPro 
    :height="800" 
    v-model="content" 
    :autoSave="true"
    @on-upload-image="uploadImg"
    :toolbars="{
      uploadImage:true,
      save:true,
      clear:true
    }"/>
    <MarkdownPreview/>
  </div>
</template>

<script>
import { MarkdownPro,MarkdownPreview  } from 'vue-meditor'
export default {
  components:{
    MarkdownPro,
    MarkdownPreview
  },
  created(){
    let that = this
    this.$axios.get("/user/queryDirs").then(response =>{
      console.log(response);
      let result = response.data.data
      that.dirOptions = result
    })
    this.$axios.get("/blog/getBlogTypes").then(response =>{
      let result = response.data.data
      console.log(result);
      that.typeOptions = result
    })
  },
  data(){
    return {
      title:'',
      intro:'',
      content:'',
      dirId:1,
      typeId:1,
      privateId:1,
      dirOptions:[],
      typeOptions:[],
      privateOptions:[{
          value:1,
          label:'公开'
        },{
          value:0,
          label:'私密'
        }]
    }
  },
  methods:{
    saveBlog(){
      let formDate = new FormData();
      formDate.append('title',this.title)
      formDate.append('intro',this.intro)
      formDate.append('content',this.content)
      formDate.append('userDir',this.dirId)
      formDate.append('typeId',this.typeId)
      formDate.append('privateId',this.privateId)
      console.log(this.content);
      this.$axios.post("/blog/addBlog",formDate).then(res => {
        console.log('saveBlog',res);
        if(res.data.data){
          alert('保存成功')
        }
      })
    },
    uploadImg(file){
      console.log(file);
      //上传图片 返回网络地址
      let formDate = new FormData()
      formDate.append('file',file)
      formDate.append('title','博客图片')
      formDate.append('privateId',1)
      formDate.append('rtId',1)
      this.$axios.post("/resource/upload",formDate).then(res => {
        let result = res.data.data
        console.log(result);
        //插入content
        this.insertImage(result)
      })

      
    },
    insertImage(remotePath){
      this.content += `\n![image](${remotePath})`
    }
    
  }
}
</script>

<style scoped>

</style>