# springcloud项目简介

总共10个Module工程,其中9个为微服务工程,构成了一个完整的微服务系统.
7个微服务系统的基础服务:
* config-server (配置中心) 
* eureka-server(注册中心)     
* uaa-service(授权中心)       
* monitor-service(聚合监控服务) 
* admin-service(聚合监控服务) 
* gateway-service(路由网关服务)
* log-service(日志服务)   

2个资源服务:
* user-service   
* blog-service 
1个common工程,为资源服务提供基本的工具类
* common
1个链路工程,使用Jar包的形式启动
* zipkin-server-2.10.1-exec.jar   
