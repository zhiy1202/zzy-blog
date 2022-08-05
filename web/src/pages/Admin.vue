<template>
  <div class="admin_container" :style="{width:adminWidth+'px',height:adminHeight+'px'}">
    <div class="admin_nav" :style="{height:adminHeight+'px'}" v-if="true">
        <router-view name="left"></router-view>
    </div>
    <div class="admin_opration">
        <router-view name="right"></router-view>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
    computed: {
        ...mapGetters(['navIsShow'])
    },
    data(){
        return{
            adminWidth: 0,
            adminHeight:0
        }
    },
    created(){
        this.$store.state.isAdmin = false
        localStorage.removeItem('admin')
        delete this.$axios.defaults.headers.common['admin']
        this.adminWidth = window.innerWidth;
        this.adminHeight = window.innerHeight;
    }
}
</script>

<style scoped>
.admin_container{
    display:flex;
    background-color: black;
    color:rgb(252, 252, 252);
}
.admin_nav{
    flex: 1;
    display:fixed;

}
.admin_opration{
    flex:3;
    display:fixed;
}
</style>