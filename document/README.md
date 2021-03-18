微服务采用技术
主体技术使用：SpringCloud+SpringCloudAlibaba
使用细节：SpringCloudSecurity+Nacos+OpenFeign+SpringGateway+Sentinel
采用注解的方式读写分离(@WriteDb,@ReadDb)，如果不声明注解，默认使用写库；
读写数据一致性问题：由于读写库采用异步同步方式,目前使用redis缓存方式解决数据一致性问题。
nanophase:
 -nanophase-center   核心业务服务
 -nanaphase-common   公共模块
 -nanphase-feign     远程调用服务 - 使用SpringNetflixOpenFeign
 -nanophase-gateway  服务网关 - 使用SpringCloudGateway
 -nanophase-register 配置中心 TODO-待改造
 -nanophase-security 授权中心 - 使用SpringCloudSecurity
 -nanophase-Sentinel 负载均衡 TODO

Sentinel：分布式系统流量防卫兵
    使用Sentinel作为流量切入点，做流量控制，负载均衡，负载保护，熔断等措施
    实时监控（待定）
JDBC(Java DataBase Connectivity)：定义程序与DB的连接规范或接口，由数据库厂商提供接口的实现
为什么使用连接池：
    连接池可以预先与DB建立一些连接，放在内存中，程序在与DB建立连接时直接去连接池中去申请，减少资源浪费情况
常用的连接池技术：Druid，C3P0都是在JDBC基础上建立
Druid：
官方网站文档：https://github.com/alibaba/druid/wiki/Druid%E8%BF%9E%E6%8E%A5%E6%B1%A0%E4%BB%8B%E7%BB%8D
使用文档：https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
SpringBoot集成Druid文档：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
Druid连接池是阿里巴巴开源的数据库连接池项目。Druid连接池为监控而生，内置强大的监控功能，监控特性不影响性能。功能强大，能防SQL注入，内置Loging能诊断Hack应用行为。

Druid不仅仅是一个数据库连接池，它还包含一个ProxyDriver，一系列内置的JDBC组件库，一个SQL Parser。 支持所有JDBC兼容的数据库，包括Oracle、MySQL、Derby、Postgresql、SQL Server、H2等等。

Druid针对oracle和mysql做了特别优化，比如Oracle的PS Cache内存占用优化，MySql的ping检测优化。Druid提供了MySql、Oracle、Postgresql、SQL-92的SQL的完整支持，这是一个手写的高性能SQL Parser，支持Visitor模式，使得分析SQL的抽象语法树很方便。简单SQL语句用时10微秒以内，复杂SQL用时30微秒。

通过Druid提供的SQL Parser可以在JDBC层拦截SQL做相应处理，比如说分库分表、审计等。Druid防御SQL注入攻击的WallFilter就是通过Druid的SQL Parser分析语义实现的 

SpringCloudSecurity + OAuth2.0 + JWT登录鉴权：
登录方式：Token令牌(与机器ip绑定，避免Token被外部机器窃取)
使用JWT是因为可以自包含Token(存储Token可以使用redis，Jwt，这里使用Jwt)，不需要服务端持久化Token，携带在请求头上就可以
JWT-header：由type：jwt（类型）和alg：HS256签名算法组成
登录（授权认证）是为了安全访问，因为HTTP无状态
SpringOAuth2.0标准是RFC6749文件，将OAuth解释为：OAuth引入了授权层，用来分离两种不同的角色。......资源所有者同意后，资源所有者可以向客户端办法令牌。
客户端通过令牌，去请求数据。由于互联网有多种场景，本标准定义了四种授权方式:
1,授权码(authorization_code)
2,隐藏式(implicit)
3,密码式(password)
4,客户端凭证(client credentials)
但是不管哪一种方案，第三方在在拿到申请令牌之前，都需要到系统备案，然后会拿到身份的识别编码(client_id,客户端ID)和(client_secret，客户端密钥)
详情：学习网站：http://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html
机器ip:
登录方式目前采用Token与机器ip绑定的方式，在获取机器ip时如果用户使用的nginx或者Apache等组件进行反向代理，则无法通过request.getRemoteAddr()
获取真实的ip地址。原因：使用了代理后，client端不会请求服务端，而是请求代理端，由代理器去请求服务端，服务端拿到的地址为代理端的地址
地址：https://www.cnblogs.com/xlhan/p/7154577.html




