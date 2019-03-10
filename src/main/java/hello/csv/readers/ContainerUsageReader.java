package hello.csv.readers;

import hello.model.Container;
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
public class ContainerUsageReader extends BasicReader {

    protected Map<String,List<Container.CUsage>> cUsages = new HashMap<>();
    protected Container tempC = new Container();

    private static Logger log = LoggerFactory.getLogger(ContainerUsageReader.class);

    @Override
    public void operateEachLine(String[] line){
        List<Container.CUsage> cList = null;
        if(cUsages.containsKey(line[0])){
            cList = cUsages.get(line[0]);
        }
        else{
            cList = new ArrayList<>(100);
        }
        cList.add(tempC.new CUsage(Integer.valueOf(line[2]),line[1],this.compactPartString(line,3,10)));
        cUsages.put(line[0],cList);
    }

    @Override
    public void bulkLines(){
        if(cUsages.isEmpty()){
            return;
        }
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,Machine.class);
        for(String key: cUsages.keySet()){
            log.info("For container: "+key);
            Update update = new Update();
            update.push("usages").each(cUsages.get(key).toArray());
            ops.updateOne(query(where("_id").is(key)),update);
        }
        ops.execute();

        cUsages.clear();
    }
}
