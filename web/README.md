# zzy-blog

## Project setup
> 安装依赖
```
npm install
```

### 运行
```
npm run serve
```

### 构建
```
npm run build
```

# admin管理页面 (http://localhost:8081/#/admin) 登录地址
# 请注意 该系统使用了 vueRouter 设置了请求拦截 需先登录才可操作指定页面
# axios 进行了统一管理
# vuex 和 axios headers 存放在localStore中 headers user:user 用于用户登录 admin:admin 用于管理员登录 值可自行设定与gateway拦截统一便可
