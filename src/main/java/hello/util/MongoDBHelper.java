package hello.util;


import hello.model.BadModel;
import hello.model.Machine;
import hello.model.Task;
import hello.repository.MachineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class MongoDBHelper {
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MachineRepository mr;

    private static Logger log = LoggerFactory.getLogger(MongoDBHelper.class);

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void checkOneMachine(String machine_id){
        Machine ms = this.mongoTemplate.findOne(query(where("_id").is(machine_id)),Machine.class,"machine");
        if(ms !=null ){
            log.info("statuses' size: "+ms.getStatuses().size()+"Usages' size:"+ms.getUsages().size());
        }
    }

    public void checkMachines(String machine_id){
        List<Machine> ms = this.mr.findByMachineId(machine_id);
        if(ms !=null && ms.size() > 0){
            log.info("statuses' size: "+ms.get(0).getStatuses().size()+"Usages' size:"+ms.get(0).getUsages().size());
        }
    }

    public void findOneMachines(String machine_id){
        BadModel bm = this.mongoTemplate.findOne(query(where("_id").is(machine_id)),BadModel.class,"machine");
        System.out.println(bm.usages.get(0).size());
    }

    public void initTaskMap(){
        mongoTemplate.dropCollection(Task.class);
        Task t = new Task();
        t.setInsNum(1000);
        t.setTaskId("fuck1");
        t.setTaskType("dag");
        List<String> statuses = new ArrayList<>();
        for(int i =0;i<10;i++){
            statuses.add("sss"+i);
        }
        t.setStatuses(statuses);
        Map<String,List<String>> ins = new HashMap<>();
        for(int i =0;i<5;i++){
            List<String> instanceUsage = new ArrayList<>();
            for(int j=0;j<3;j++){
                instanceUsage.add("usa"+j);
            }
            ins.put("instance"+i,instanceUsage);
        }
        t.setInstances(ins);
        mongoTemplate.insert(t);
    }

    public void addTaskMap(){
        Task t = mongoTemplate.findOne(query(where("_id").is("fuck1")),Task.class);
        if(t == null)
            return;
        List<String> statuses = new ArrayList<>();
        for(int i =10;i<20;i++){
            statuses.add("sss"+i);
        }
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,Task.class);
        Update up = new Update();
        up.push("statuses").each(statuses.toArray());
        ops.updateOne(query(where("_id").is("fuck1")),up);

        Update up2 = new Update();
        List<String> instanceUsage = new ArrayList<>();
        for(int j=0;j<3;j++){
            instanceUsage.add("usa"+j);
        }
        up2.push("instances.instance0").each(instanceUsage.toArray());
        ops.updateOne(query(where("_id").is("fuck1")),up2);

        ops.execute();
    }
}
