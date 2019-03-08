package hello.util;


import hello.model.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class MongoDBHelper {
    @Autowired
    protected MongoTemplate mongoTemplate;

    private static Logger log = LoggerFactory.getLogger(MongoDBHelper.class);

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void checkOneMachine(String machine_id){
        List<Machine> ms = this.mongoTemplate.find(query(where("_id").is(machine_id)),Machine.class,"machine");
        if(ms !=null && ms.size() > 0){
            log.info("statuses' size: "+ms.get(0).getStatuses().size()+"Usages' size:"+ms.get(0).getUsages().size());
        }
    }
}
