# Description

This is a project to analysis Alibaba trace data. The schema is in (https://github.com/alibaba/clusterdata.git).

The source code is based on the spring boot MongoDB, which is easy to refactor ( https://github.com/spring-guides/gs-accessing-data-mongodb.git ).

# key methods
1. The data size is near 300GB and I use MongoDB to store them.
2. Clusterdata-java can transpose all the CSV records to a line to a specific instance, task, job, container or machine.

# Steps
1. Transfering all the data into a format which is readable and configurable.
2. Calculation.