package hello.csv.readers;

import hello.model.Machine;
import hello.model.StatusInt;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class TaskMetaReader extends BasicReader {
    protected Set<String> ids = new HashSet<>();

    protected Map<String,Machine> currentMachines = new HashMap<>();

    @Override
    public void init(){
        mongoTemplate.dropCollection(Machine.class);
    }

    @Override
    public void operateEachLine(String[] line){
        Machine m = null;
        if(currentMachines.containsKey(line[0])){
            m = currentMachines.get(line[0]);
        }
        else{
            m = new Machine();
            m.setDisasterLevel1(line[2]);
            m.setDisasterLevel2(line[3]);
            m.setCpuNum(line[4]);
            m.setMemSize(line[5]);
            m.setMachineId(line[0]);
            this.currentMachines.put(line[0],m);
        }
        m.getStatuses().add(new StatusInt(Integer.valueOf(line[1]),line[6]));
    }

    @Override
    public void bulkLines(){
        if(currentMachines.isEmpty()){
            return;
        }
        List<Machine> insertMachines = new ArrayList<>();
        List<Machine> updateMachines = new ArrayList<>();
        for(String key: currentMachines.keySet()){
            if(!ids.contains(key)){
                insertMachines.add(currentMachines.get(key));
                ids.add(key);
            }
            else{
                updateMachines.add(currentMachines.get(key));
            }
        }
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,Machine.class);
        for(Machine m : insertMachines){
            ops.insert(m);
        }
        for(Machine m: updateMachines){
            Update update = new Update();
            update.push("statuses",m.getStatuses().toArray());
            ops.updateOne(query(where("_id").is(m.getMachineId())),update);
        }
        ops.execute();

        insertMachines.clear();
        updateMachines.clear();
        currentMachines.clear();
    }
}
