package hello.csv.readers;

import hello.model.Machine;
import hello.model.StatusInt;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.query.Update;

import javax.crypto.Mac;
import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class MachineMetaReader extends BasicReader {
    protected Set<String> ids = new HashSet<>();

    protected Map<String,Machine> currentMachines = new HashMap<>();

    @Override
    public void operateEachLine(String[] line){
        Machine m = null;
        if(currentMachines.containsKey(line[0])){
            m = currentMachines.get(m);
        }
        else{
            m = new Machine();
            m.setDisaster_level_1(line[2]);
            m.setDisaster_level_2(line[3]);
            m.setCpu_num(line[4]);
            m.setMem_size(line[5]);
            m.setMachine_id(line[0]);
            this.currentMachines.put(line[0],m);
        }
        m.getStatuses().add(new StatusInt(Integer.valueOf(line[1]),line[6]));
    }

    @Override
    public void bulkLines(){
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
            update.addToSet("statuses");
            ops.updateOne(query(where("id").is(m.getMachine_id())),update);
        }
        ops.execute();

        insertMachines.clear();
        updateMachines.clear();
        currentMachines.clear();
    }
}
