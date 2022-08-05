import axios from 'axios';
// 创建axios实例
var instance = axios.create({
  baseURL: 'http://localhost:81/',
  timeout: 1000 * 12});
// axios.defaults.withCredentials=true;
instance.defaults.withCredentials=true
instance.interceptors.request.use(
  config => {
    // console.log('发送请求')
    // console.log(localStorage.getItem('user'))
    if (localStorage.getItem('user')) {
      // console.log('请求头加入token')
      config.headers.user = localStorage.getItem('user')
    }
 
    return config
 
  },
  error => {
    console.log('请求失败！！')
    console.log(error)
    return Promise.reject(error)
 
  })
instance.interceptors.request.use(
    config => {
      // console.log('发送请求')
      // console.log(localStorage.getItem('admin'))
      if (localStorage.getItem('admin')) {
        // console.log('请求头加入token')
        config.headers.admin = localStorage.getItem('admin')
      }
   
      return config
   
    },
    error => {
      console.log('请求失败！！')
      console.log(error)
      return Promise.reject(error)
   
    })  
 
//异步请求后，判断token是否过期
instance.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 406:
          localStorage.removeItem('user');
          this.$router.push('/welcome');
          break;
        case 500:
          localStorage.removeItem('user');
          this.$router.push('/welcome');
          break;
      }
    }
  }
)
export default instance;