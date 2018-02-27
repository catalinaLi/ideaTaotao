## 更新
将所有的服务生产者修改为以jar包的形式用springboot的方式进行启动！

## 关于淘淘商城 
淘淘商城商城应该是一个网上臭名昭著的电商练习项目了，本着学习的目的把他写了一遍。说实话，还是受益良多的。
废话不多说了，我们来看一下他的架构
![taotao_arch](http://ou3np1yz4.bkt.clouddn.com/taotao_arch.png)
项目采用SOA的架构，使用dubbo作为服务中间件。把工程拆分成服务层、表现层两个工程。服务层中包含业务逻辑，只需要对外提供服务即可。表现层只需要处理和页面的交互，业务逻辑都是调用服务层的服务来实现。

**前台**
![taotao_portal](http://ou3np1yz4.bkt.clouddn.com/taotao_portal.png)

**后台**
![taotao_admin](http://ou3np1yz4.bkt.clouddn.com/taotao_admin.png)
前端页面不是这次练习的重点。
## 服务介绍
- taotao-manage 
    后台服务层，提供后台基础服务。
- taotao-manage-web
    后台表现层，调用了manage、content、search的服务。
- taotao-portal-web
    前台表现层，调用了content的服务。
- taotao-content
    CMS服务层，提供内容管理。
- taotao-search
    搜索服务层，提供搜索基础服务。
- taotao-search-web
    搜索表现层，调用了search的服务。
- taotao-item-web
    商品详情表现层，调用了manage的服务。
- taotao-sso
    单点登录服务层，提供sso基础服务。
- taotao-sso-web
    单点登录表现层，调用了sso的服务。
- taotao-cart
    购物车服务层，提供了购物车的基础服务。
- taotao-cart-web
    购物车表现层，调用了cart、manage、sso的服务。
- taotao-order
    订单服务层，提供了订单基础服务。
- taotao-order-web
    订单表现层，调用了order、cart、sso的服务
## 主要模块介绍
### SSO单点登录模块
SSO英文全称Single Sign On，单点登录。SSO是在多个应用系统中，用户只需要登录一次就可以访问所有相互信任的应用系统。它包括可以将这次主要的登录映射到其他应用中用于同一个用户的登录的机制。它是目前比较流行的企业业务整合的解决方案之一。

在传统的单机工程下用户登录是没有问题的，但是集群环境下会出现要求用户多次登录的情况。

解决方案：

1、配置tomcat集群。配置tomcatSession复制。节点数不要超过5个。
2、可以使用Session服务器，保存Session信息，使每个节点是无状态。需要模拟Session。

淘淘商城采用了第二种方案，每次登录的时候后台生成一个随机的Token来模拟Session中的JSESSIONID。将这个Token在后台保存在Redis中，前台保存在Cookie中，这样每次登录的时候都进入SSO模块来处理登录的逻辑。

### 购物车模块
购物车是一个独立的表现层工程。添加购物车不要求登录。可以指定购买商品的数量。
这样我们在使用购物车的情景就分为用户未登录状态和已登录状态
这里我们使用拦截器来判断是否登录。在不登录的情况下把购物车信息写入cookie，在已登录的情况下把购物车信息写入Redis。

### 订单模块
在购物车页面点击“去结算”按钮，跳转到订单确认页面。
主要使用拦截器来判断用户的登录情况，从不同的登录情况来判断用户购物车里商品的数量。



## 使用的中间件
### Nginx
Nginx是一款高性能的http服务器/反向代理服务器及电子邮件（IMAP/POP3）代理服务器。
具体使用可以看[Nginx初探究：安装与简单使用](http://catalinali.top/2017/helloNginx/)
### FastDFS分布式文件系统
FastDFS是一个开源的轻量级分布式文件系统，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。特别适合中小文件（建议范围：4KB < file_size <500MB），对以文件为载体的在线服务，如相册网站、视频网站等。
### Redis
Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
具体使用可以看[走进Redis：Redis的安装、使用以及集群的搭建](http://catalinali.top/2017/buildredis/)
### 搜索应用服务器Solr
Solr是一个独立的企业级搜索应用服务器，它对外提供类似于Web-service的API接口。用户可以通过http请求，向搜索引擎服务器提交一定格式的XML文件，生成索引；也可以通过Http Get操作提出查找请求，并得到XML格式的返回结果。
### 消息服务Activemq
ActiveMQ 是Apache出品，最流行的，能力强劲的开源消息总线。ActiveMQ 是一个完全支持JMS1.1和J2EE 1.4规范的 JMS Provider实现，尽管JMS规范出台已经是很久的事情了，但是JMS在当今的J2EE应用中间仍然扮演着特殊的地位。
具体使用可以看[ActiveMQ从入门到实践](http://catalinali.top/2017/useMQ/)
