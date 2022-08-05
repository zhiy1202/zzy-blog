<template>
  <div>
    <ul>
        <template v-for="item,index in blogList" >
            <li @click="detail(item.blogId)" :key="index">
                <BlogItem
                :title="item.title"
                :intro="item.intro"
                ></BlogItem>
            </li>
        </template>
        
    </ul>
  </div>
</template>

<script>
import BlogItem from '../components/BlogItem.vue'
import {mapGetters,mapActions} from 'vuex'
export default {
    name:'BlogList',
    computed:{
        ...mapGetters(['blogList'])
    },
    components:{
        BlogItem
    },
    data(){
        return{
            pageBlogList:[],
            pageIndex:1,
            pageLength: 2000
        }
    },
    created(){
        this.$store.commit('RemoveBlogList')
    },
    mounted(){
        let routePath = this.$route.matched[1].path
        console.log(routePath);
        let that = this
        if (routePath === '/page/:count') {
            this.updateBlogList(this.pageIndex)
            document.addEventListener("mousewheel",that.pageFunc)
        }else if (routePath === '/userBlog/:dirId/:pageIndex'){
            this.$store.commit('RemoveBlogList')
            document.addEventListener("mousewheel",that.userBlogFunc,false)
        }else if (routePath === '/typeBlog/:typeId/:pageIndex'){
            this.$store.commit('RemoveBlogList')
            document.addEventListener("mousewheel",that.typeBlogFunc,false)
        }
    },
    methods:{
        detail(blogId){
            this.$router.push(`/blog/${blogId}`)
        },
        pageFunc(){
            var scrollTop = document.body.scrollTop+document.documentElement.scrollTop
            console.log(scrollTop);
            if (scrollTop >= this.pageLength * this.pageIndex) {
                    let pageIndex = scrollTop / this.pageLength
                    this.pageIndex = Math.ceil(pageIndex)
                    this.$router.push('/page/'+this.pageIndex)
                    this.updateBlogList(this.pageIndex)
                }
        },
        userBlogFunc(){
            var scrollTop = document.body.scrollTop+document.documentElement.scrollTop
            console.log(scrollTop);
            if (scrollTop >= this.pageLength * this.pageIndex) {
                    let pageIndex = scrollTop / this.pageLength
                    this.pageIndex = Math.ceil(pageIndex)
                    let dirId = this.$route.params.dirId
                    this.$router.push(`/userBlog/${dirId}/${this.pageIndex}`)
                    this.updateBlogListByDirId({dirId:dirId, pageIndex:this.pageIndex})
                }
        },
        typeBlogFunc(){
            var scrollTop = document.body.scrollTop+document.documentElement.scrollTop
            console.log(scrollTop);
            if (scrollTop >= this.pageLength * this.pageIndex) {
                    let pageIndex = scrollTop / this.pageLength
                    this.pageIndex = Math.ceil(pageIndex)
                    let typeId = this.$route.params.typeId
                    this.$router.push(`/typeBlog/${typeId}/${this.pageIndex}`)
                    this.updateBlogListByTypeId({typeId:typeId, pageIndex:this.pageIndex})
                }
        },
        ...mapActions(['updateBlogList','updateBlogDetail','updateBlogListByDirId','updateBlogListByTypeId'])
    },
    beforeDestroy(){
        document.removeEventListener("mousewheel",this.pageFunc,false);
        document.removeEventListener("mousewheel",this.userBlogFunc,false);
        document.removeEventListener("mousewheel",this.typeBlogFunc,false);
    }

}
</script>

<style>

</style>