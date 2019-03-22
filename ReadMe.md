# Description

This is a project to analysis Alibaba trace data. The schema is in (https://github.com/alibaba/clusterdata.git).

The source code is based on the spring boot MongoDB, which is easy to refactor ( https://github.com/spring-guides/gs-accessing-data-mongodb.git ).

Installing MongoDB ( https://www.cnblogs.com/weschen/p/8213746.html  https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/?_ga=2.110920069.1240577217.1551961329-1838636681.1551961329).

Repository and MongoDB template are the 2 way to deal with DB ( https://www.cnblogs.com/atliwen/p/6644627.html ).

Batch process ( https://blog.csdn.net/sinat_24044957/article/details/80646292 ).


# key methods
1. The data size is near 300GB and I use MongoDB to store them.
2. Clusterdata-java can transpose all the CSV records to a line to a specific instance, task, job, container or machine.

# Steps
1. Transfering all the data into a format which is readable and configurable.
2. Calculation.

# 当前存在的问题与解决办法
1. 核心：没有必要按照单个的timestamp所有的数据进行遍历和再次入口，只需要按照时间间隔（暂定为半个小时，甚至是若干分钟进行分析），这样八天存存放的整体数据量就不会太大；
2. 以半个小时为例：4034台主机的CPU使用率最多占用40MB内存；
3. 以半个小时为例：7w个容器的CPU使用率最多占用800MB内存；
4. 以半个小时为例：260w个实例，累计消耗不超过30GB的内存；
5. 对task和instance状态的问题，其很多字段是冗余的，不会发生变化（计划CPU与内存），第一步先不用算的太细致
6. 当前只关心某个时间区间整个集群的整体状态，不关心每个人任务实际部署的地方，可以按照时间点进行划分，不用太关注每个机器的具体使用情况；

# 进一步要解决的问题
1. 重新计算job与task的分布；
2. 统计整体Machine和容器的资源分布，按照时间段进行资源聚合；
3. job的DAG的关系，从开始到结束的长度，关键路径；
4. 每个task规划资源和各个副本请求资源的对比；
5. 每个副本执行情况，是否时间很长，是否之间差异较大，是否存在迁移。