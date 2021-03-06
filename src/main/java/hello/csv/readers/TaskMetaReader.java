package hello.csv.readers;

import hello.model.Task;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class TaskMetaReader extends BasicReader {
    protected Set<String> jobTasks = new HashSet<>();

    protected Map<String, Task> currentTasks = new HashMap<>();

    @Override
    public void init(){
        mongoTemplate.dropCollection(Task.class);
    }

    @Override
    public void operateEachLine(String[] line){
        Task t = null;
        if(currentTasks.containsKey(line[3]+","+line[0])){
            t= currentTasks.get(line[3]+","+line[0]);
        }
        else{
            t = new Task();
            t.setInsNum(Integer.valueOf(line[1]));
            t.setPlanCpu(Double.valueOf(line[7]));
            t.setPlanMem(Double.valueOf(line[8]));
            t.setTaskId(line[3]+","+line[0]);
            t.setTaskType(line[2]);
            this.currentTasks.put(t.getTaskId(),t);
        }
    }

    @Override
    public void bulkLines(){
        if(currentTasks.isEmpty()){
            return;
        }
        List<Task> insertTasks = new ArrayList<>();
        List<Task> updateTasks = new ArrayList<>();
        for(String key: currentTasks.keySet()){
            if(!jobTasks.contains(key)){
                insertTasks.add(currentTasks.get(key));
                jobTasks.add(key);
            }
            else{
                updateTasks.add(currentTasks.get(key));
            }
        }
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,Task.class);
        for(Task m : insertTasks){
            ops.insert(m);
        }
        for(Task m: updateTasks){
            Update update = new Update();
            update.set("planCpu",m.getPlanCpu());
            update.set("planMem",m.getPlanMem());
            ops.updateOne(query(where("_id").is(m.getTaskId())),update);
        }
        ops.execute();

        insertTasks.clear();
        //updateTasks.clear();
        currentTasks.clear();
    }
}
