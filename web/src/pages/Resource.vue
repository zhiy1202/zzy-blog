<template>
  <div class="resource">
    <ResourceList></ResourceList>
  </div>
</template>

<script>
import ResourceList from '../components/resource/ResourceList.vue'
export default {
  components:{
    ResourceList
  },
  data(){
    return{
    }
  }
  
}
</script>

<style scoped>
ul{
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
ul li{
  margin-top: 20px;
}

</style>