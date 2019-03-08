package hello.csv.readers;

import hello.model.Machine;
import hello.model.UsageInt;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class MachineUsageReader extends BasicReader {

    protected Map<String,List<UsageInt>> mUsages = new HashMap<>();

    @Override
    public void operateEachLine(String[] line){
        List<UsageInt> uList = null;
        if(mUsages.containsKey(line[0])){
            uList = mUsages.get(line[0]);
        }
        else{
            uList = new ArrayList<>(100);
        }
        uList.add(new UsageInt(Integer.valueOf(line[1]),this.compactPartString(line,2,8)));
        mUsages.put(line[0],uList);
    }

    @Override
    public void bulkLines(){
        if(mUsages.isEmpty()){
            return;
        }
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,Machine.class);
        for(String key: mUsages.keySet()){
            System.out.println("machine:"+key+"size:"+mUsages.get(key).size());
            Update update = new Update();
            update.addToSet("usages",mUsages.get(key));
            ops.updateOne(query(where("_id").is(key)),update);
        }
        ops.execute();

        mUsages.clear();
    }
}
