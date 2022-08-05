import Vue from 'vue';
import Router from 'vue-router'
Vue.use(Router)

import Login from '../components/Login'
import Register from '../components/Register'
import BlogList from '../components/BlogList'
import BlogDetail from '../components/BlogDetail'
import BlogType from '../components/BlogType'
import UserDetail from '../components/UserDetail'
import BlogComm from '../components/BlogComm'

import ResourceLeft from '../components/resource/ResourceLeft'

import MyselfLeft from '../components/myself/MyselfLeft'
import MyselfRight from '../components/myself/MyselfRight'
import UserLike from '../components/myself/UserLike'
import UserHistory from '../components/myself/UserHistory'
import CreateBlog from '../components/myself/CreateBlog'
import DirManange from '../components/myself/DirManange'
import AllBlog from '../components/myself/AllBlog'
import AllComm from '../components/myself/AllComm'
import UploadResource from '../components/myself/UploadResource'
import ResourceManage from '../components/myself/ResourceManage' 
import UpdateBlog from '../components/myself/UpdateBlog' 

import Welcome from '../pages/Welcome.vue'
import Index from '../pages/Index.vue'
import Resource from '../pages/Resource.vue'
import MySelf from '../pages/MySelf.vue'
import Admin from '../pages/Admin.vue'

import AdminNav from '../components/admin/AdminNav'
import AdminWelcome from '../components/admin/AdminWelcome'
import UserManage from '../components/admin/UserManage'
import BlogManage from '../components/admin/BlogManage'
import AdminResourceManage from '../components/admin/ResourceManage'
import AdminLogin from '../components/admin/AdminLogin'

const router = new Router({
    routes:[
        //userclient
        {
            path: '/',
            component: Index,
            redirect: '/page/1',
            children:[
                //所有博客
                {
                    path: 'page/:count',
                    components: {
                        default: BlogList,
                        left: BlogType
                    },
                    
                },  
                //博客详情
                {
                    path: 'blog/:blogId',
                    components: {
                        default: BlogDetail,
                        left: UserDetail,
                        right:BlogComm
                    }
                },
                //系统分类
                {
                    path: 'typeBlog/:typeId/:pageIndex',
                    components: {
                        default: BlogList,
                        left: BlogType
                    },
                    
                },
                //按用户目录划分博客
                {
                    path: 'userBlog/:dirId/:pageIndex',
                    components: {
                        default: BlogList,
                        left: UserDetail
                    },
                    
                },
                //资源
                {
                    path: '/resource',
                    components:{
                        default: Resource,
                        left: ResourceLeft
                    }
                },
                //用户个人信息
                {
                    path: '/myself',
                    components:{
                        default:MySelf,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //用户喜欢
                {
                    path: '/userLike',
                    components:{
                        default:UserLike,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //用户历史
                {
                    path: '/userHistory',
                    components:{
                        default:UserHistory,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //新建博客
                {
                    path: '/createBlog',
                    components:{
                        default:CreateBlog,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //更新博客
                {
                    path: '/updateBlog/:blogId',
                    components:{
                        default:UpdateBlog,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //用户目录管理
                {
                    path: '/dirManange',
                    components:{
                        default:DirManange,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //用户所有博客
                {
                    path: '/allBlog',
                    components:{
                        default:AllBlog,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //博客所有评论
                {
                    path: '/allComm',
                    components:{
                        default:AllComm,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //所有图片
                {
                    path: '/allImg',
                    components:{
                        default:ResourceManage,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //所有音频
                {
                    path: '/allAudio',
                    components:{
                        default:ResourceManage,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //所有视频
                {
                    path: '/allVideo',
                    components:{
                        default:ResourceManage,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //所有文件
                {
                    path: '/allFile',
                    components:{
                        default:ResourceManage,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //更新资源
                {
                    path: '/uploadResource',
                    components:{
                        default:UploadResource,
                        left:MyselfLeft,
                        right:MyselfRight
                    }
                },
                //用户访问主页
                {
                    path:'/lookUser/:id',
                    components:{
                        left:UserDetail,
                        default:BlogList

                    }
                }
            ]
        },
        //welcome
        {
          path: '/welcome',
          component: Welcome,
          redirect: '/welcome/login',
          children:[
                {
                    path: 'login',
                    component: Login
                },
                {
                    path: 'register',
                    component: Register
                }
          ]
        },
        //admin
        {
            path: '/admin',
            component: Admin,
            redirect:'/admin/login',
            children:[
                {
                    path: 'login',
                    components:{
                        left:AdminNav,
                        right:AdminLogin
                    }
                },
                {
                    path: 'welcome',
                    components:{
                        left:AdminNav,
                        right:AdminWelcome
                    }
                },
                {
                    path: 'adminUser',
                    components:{
                        left:AdminNav,
                        right:UserManage
                    }
                },
                {
                    path: 'adminBlog',
                    components:{
                        left:AdminNav,
                        right:BlogManage
                    }
                },
                {
                    path: 'adminResourcce',
                    components:{
                        left:AdminNav,
                        right:AdminResourceManage
                    }
                },
            ]
        }
    ]
})
export default router