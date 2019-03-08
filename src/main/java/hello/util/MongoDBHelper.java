package hello.util;


import hello.model.BadModel;
import hello.model.Machine;
import hello.repository.MachineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
}
