nanophase-search站内检索
基于elasticsearch实现
如果使用mysql(B+tree)进行模糊匹配，全表扫描(时间复杂度O(n))
除了性能问题，数据库不支持分词
倒排索引解决性能问题：
3GHZ的CPU 64位（8byte），64位是带宽，1024 * 8 > 100G,内存大约20-50G；硬盘一般几百兆速度
最快的固态硬盘大约2-3G读取速度
数据结构：
    1，包含这个关键词的document list
    2,关键词在这个document中出现的次数 TF(term frequency)
    3,关键词在整个索引中出现的次数IDF(inverse document frequency)
    IDF也可称为相关度，IDF越高相关性越低，假设“的”在每个文章中都出现了，说明相关性不大
    4,关键词在当前document中出现的次数
    5,每个document的长度越长，相关度越低
    6,包含这个关键词的所有doc的平均长度
lucene（jar包）：
    lucene创建了倒排索引，并提供复杂API
ES相对lucene：
    1，进一步封装lucene，屏蔽其复杂性，并且集群自动发现
    2，自动维护数据在多个节点上的建立
    3，自动对搜索请求做负载均衡
    4，自动维护冗余副本保证部分节点宕机情况下不会丢失数据
    5，API提供上提供了聚合分析，基于地理位置，符合查询，同义词处理，相关度排名等
ES具体应用（github代码几千亿行）：
    1，基本检索，高亮，搜索推荐等
    2，网站的用户行为日志（用户点击，浏览，收藏，评论），作为分析数据
    3，BI（Business Intelligence 商业智能）数据分析，数据挖掘与统计
    4，ELK（elasticsearch[数据存储],Logstash[日志采集],Kibana[可视化]）
ES核心概念：
    1.cluster集群，每个集群至少包含两个节点
    2，集群的一个节点不代表一个服务器（一台机器可以有多个节点）
    3，ES最小的数据单元Field，一个数据字段可以和index和type定位一个doc
    4,document，ES最小的数据行，json格式
    5，index，相同或类似的doc，如员工索引，商品索引
    6，Type 逻辑上的数据分类，通常一个index划分为多个type
shard分片：
（Primary shard主分片,Replica shard副本分片）：
副本分片和主分片的数据是一样的，相当于备份，他们不能放在同一个节点上，
副本分片主要起提升性能，保护数据的功能
分片存储数据
1，在一个index中，默认有5个主分片，每一个主分片都会分配一个副本分片。主分片在创建索引时设置好
如果像修改，必须重新构建索引
（主分片可以有多个RShard，ES强要求不能主分片和副本不能存在同一个节点）
2，每一个shard都是一个lucene实例
3，ES会自动在节点上对shard做负载均衡
4，一个document不会存在多个PShard上，但是可以存在多个RShard上[副本分片主要承受查询压力]
ES的集群健康状态分为3中
Green：所有的Primary和Replica均为可用
Yellow：至少一个Replica不可用，但是所有的Primary均可用
Red：至少一个Primary不可用
一些配置：
1,voting：投票节点
Node.voting_only = true
作为选举master的投票节点，即便配置了node.master = true（候选节点）也不会参选
2,coordinating:协调节点
每一个节点都是隐士的协调节点（转发请求），如果同时配置了node.master = false和 node.data（数据节点） = false
那么此节点仅成为协调节点
node.master & node.data是E是的默认配置，既作为候选节点也是数据节点，但是在集群情况下，这样配置的节点
一旦被选为了master，承受的压力是很大的，一般来讲master只作为集群的管理者，承担较为轻量的任务，
比如创建删除索引，分片均衡等
node.master = true,node.data = false;


    
    