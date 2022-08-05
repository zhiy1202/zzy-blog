<template>
  <div>
      <ul>
        <li>
          <el-input @blur="searchResource" v-model="input" placeholder="请输入资源名"></el-input>
        </li>
        <li>
          <span>{{title}}</span>
        </li>
        <template v-for="item in resourceType">
          <li :key="item.rtId" @click="resourceList(item.rtId)">
            {{item.typeName}}
          </li>
        </template>
        
      </ul>
  </div>
</template>

<script>
import {mapActions , mapGetters} from 'vuex'
export default {
    computed:{
      ...mapGetters(['resourceType'])
    },
    created(){
      this.getResourceType()
    },
    data(){
      return{
        title:'资源分类',
        input:''
      }
  },
  methods: {
    resourceList(rtId){
      this.getResourceList(rtId)
    },
    searchResource(){
      this.getResourceListByTitle(this.input)
    },
    ...mapActions(['getResourceType','getResourceList','getResourceListByTitle'])
  }

}
</script>

<style scoped>
  ul{
    width: 100%;
    height:auto;
  }
  ul li{
    cursor: pointer;
    text-align: center;
    line-height:40px;
    width: 100%;
    height: 40px;
    border-bottom:1px solid black;
    border-right:1px solid black;
    transition: all 0.2s;
  }
  ul li:hover{
    background-color: skyblue;
  }
</style>