# device-manager
## 高校设备管理系统
基于spring boot + vue.js构建，前后端分离\
认证使用jwt并且缓存到redis中\
接口级权限控制

## 部署
1.前端部署于nginx下，修改nginx.conf配置文件，添加反向代理配置
```
location ^~ /dev-manager/api_v1/ {
	proxy_pass http://localhost:8080;
}
```