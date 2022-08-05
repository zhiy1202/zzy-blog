<template>
  <div>
    <div>
      <span>
        文件名: 
      </span>
      <el-input v-model="title" placeholder="请输入标题"></el-input>
    </div>

    <div>
      <span>
        选择类型: 
      </span>
      <el-select v-model="value" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <span>
        是否公开: 
      </span>
      <el-select v-model="privateId" placeholder="请选择">
        <el-option
          v-for="item in priOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>

  <div>
    <el-upload
      class="upload-demo"
      action=""
      :on-success="handleSucess"
      :on-preview="handlePreview"
      :http-request="upload"
      :limit="1">
      <el-button size="small" type="primary">点击上传</el-button>
    </el-upload>
    <el-button type="success" @click="save">保存</el-button>
  </div>

  </div>
</template>

<script>
export default {
  data() {
      return {
        title:'',
        privateId:1,
        file:null,
        priOptions:[{
          value:1,
          label:'公开'
          },{
            value:0,
            label:'不公开'
          }
        ],
        options: [{
          value: 1,
          label: '图片'
        }, {
          value: 2,
          label: '音乐'
        }, {
          value: 3,
          label: '视频'
        }, {
          value: 4,
          label: '文件'
        }],
        value: 1
      }
    },
    methods: {
      //确认已填
      check(){
        if (this.title != '' && this.file != null){
          return true;
        }else{
          return false;
        }
      },
      save(){
        if (this.check()) {
          //发送请求
          console.log(this.file);
          let formDate = new FormData()
          formDate.append('title', this.title)
          formDate.append('rtId', this.value)
          formDate.append('privateId', this.privateId)
          formDate.append('file',this.file)

          this.$axios.post('/resource/upload',formDate).then(res=>{
            let result = res.data.data
            console.log(result);
          })
        }else{
          alert('所有选项不能为空')
        }
      },
      upload(a){
        this.file = a.file
      },
      handlePreview(file) {
        console.log(file);
      },
      handleSucess(a,b){
        console.log(a,b);
      }
    }
}
</script>

<style scoped>

</style>