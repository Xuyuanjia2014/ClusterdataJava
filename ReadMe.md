# Description

This is a project to analysis Alibaba trace data. The schema is in (https://github.com/alibaba/clusterdata.git).

The source code is based on the spring boot MongoDB, which is easy to refactor ( https://github.com/spring-guides/gs-accessing-data-mongodb.git ).

Installing MongoDB ( https://www.cnblogs.com/weschen/p/8213746.html ).

Repository and MongoDB template are the 2 way to deal with DB ( https://www.cnblogs.com/atliwen/p/6644627.html ).

Batch process ( https://blog.csdn.net/sinat_24044957/article/details/80646292 ).


# key methods
1. The data size is near 300GB and I use MongoDB to store them.
2. Clusterdata-java can transpose all the CSV records to a line to a specific instance, task, job, container or machine.

# Steps
1. Transfering all the data into a format which is readable and configurable.
2. Calculation.