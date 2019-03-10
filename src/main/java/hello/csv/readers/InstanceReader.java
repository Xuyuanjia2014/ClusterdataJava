package hello.csv.readers;

import hello.model.Machine;
import hello.model.UsageInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class InstanceReader extends BasicReader {

    protected Map<String,Map<String,List<String>>> taskInstances = new HashMap<>();

    private static Logger log = LoggerFactory.getLogger(InstanceReader.class);

    @Override
    public void operateEachLine(String[] line){
        Map<String,List<String>> instanceUsage = null;
        if(taskInstances.containsKey(line[2]+","+line[1])){
            instanceUsage = taskInstances.get(line[2]+","+line[1]);
        }
        else{
            instanceUsage = new HashMap<>();
        }
        List<String> usages = null;
        if(instanceUsage.containsKey(line[0])){
            usages = instanceUsage.get(line[0]);
        }
        else{
            usages = new ArrayList<>();
        }
        usages.add(this.compactPartString(line,4,13));
        instanceUsage.put(line[0],usages);
        taskInstances.put(line[2]+","+line[1],instanceUsage);
    }

    @Override
    public void bulkLines(){
        if(taskInstances.isEmpty()){
            return;
        }
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,Machine.class);
        for(String key: taskInstances.keySet()){
            log.info("For Job tasks:"+ key);
            Update update = new Update();
            Map<String,List<String>> instanceUsages = taskInstances.get(key);
            for(String instanceName : instanceUsages.keySet()){
                update.push("instances."+instanceName).each(instanceUsages.get(instanceName).toArray());
            }
            ops.updateOne(query(where("_id").is(key)),update);
        }
        ops.execute();

        taskInstances.clear();
    }
}
