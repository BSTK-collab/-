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
    
    