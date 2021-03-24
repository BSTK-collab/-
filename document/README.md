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
1，JWT-header：由type：jwt（类型）和alg：HS256签名算法组成，主要部分就是签名算法
2，载荷(payload)：用户的唯一标识，过期时间等信息。这个部分包括header都是使用base64加密，可以进行反推解密
为什么使用base64加密：也可以说压缩算法，考虑到特殊字符的情况
3，签名：签名 = hash(base64Header + base64Payload + 密文)
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
Token被截获问题：ssl，tls传输加密，非对称加密
关于HTTP：
当输入www.baidu.com时发生了什么？
1，浏览器->协议栈->网卡->网络中
首先浏览器会向服务端发送请求，获取该网页数据，但是浏览器不会负责消息的传送，而是委托操作系统中的网络控制机制发送给服务器
或者说数字信息搬运机制，这个机制首先出场的是协议栈（网络控制软件），这个机制会将消息打包，并附加地址等信息，将打包好的
信封传递给网卡（负责以太网和无线网的硬件），网卡，会将消息包转换为电信号通过网线发送出去
这时，消息包进入了网络
2，集线器 交换机 路由器
接下来的数据出入形式与我们接入互联网的方式有关，可以通过家庭或公司的局域网接入互联网，或者直接接入互联网 
假设客户计算机是通过家庭或公司的局域网接入互联网，那么网卡发送的包会先经过交换机，最后来到互联网路由器，后面就是互联网
网络运营商负责将包送到目的地
3，接入网（电话线，专线 光线等），网络运营商 -》防火墙-》web服务器（还原请求信息）同样是协议栈完成
浏览器：解析url，生成HTTP消息，获取服务器域名对应的ip，委托操作系统
为什么使用ip地址定位目标，而不是服务器名称？
ip地址的长度是32比特，也就是4个字节，域名可能需要几十上百的字符，处理起来增加路由器负担，长度不固定，做无用功
目前采用的方式是路由器使用ip地址，人们使用域名，解决这个问题的是DNS，通过DNS查询ip称为域名解析，负责执行解析的成为解析器
解析器位于操作系统的Socket库中，调用解析器代码(getHostByName(www.baidu.com)),返回ip
解析器根据DNS的规格 生成请求信息发送DNS（发送请求同样是协议栈完成），协议栈通过网卡将消息发送给DNS
（HTTP 消息是用文本编写的，但 DNS 消息是使用二进制数据编写的。）
数据如何进行收发？
拿到ip地址后，委托协议栈进行消息发送，同样需要使用Socket库，Socket库是如何收发的？
客户端和服务端都会创建套接字进行连接，数据发送完毕后，套接字形成的管道就会断开。创建套接字，连接，传输数据，断开这些操作
也都是协议栈完成的，套接字创建后协议栈会返回描述符，用来区分不同的套接字
在调用connect连接后，write数据后，协议栈不会马上就发送数据，而是暂存在缓冲区，因为不确定应用程序一次性会写入多少数据
协议栈也无法控制应用程序一次会写入多少数据，所以协议栈会根据MTU参数进行判断，判断一个网络包的最大长度，但是累计多少的数据
发送不同的操作系统有不同的实现，以太网中一般最大是1500字节（MTU也会占用一些）
当接收的数据量接近该数值是，会被打包进行发送、这里也会维护一个计时器，不能因为数据量小就永远发不出去
