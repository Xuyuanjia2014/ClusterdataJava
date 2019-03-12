package hello.csv.readers;

import hello.model.Container;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class ContainerMetaReader extends BasicReader {
    protected Set<String> containerIds = new HashSet<>();

    protected Map<String, Container> currentContainers = new HashMap<>();

    @Override
    public void init(){
        mongoTemplate.dropCollection(Container.class);
    }

    @Override
    public void operateEachLine(String[] line){
        Container container = null;
        if(currentContainers.containsKey(line[0])){
            container = currentContainers.get(line[0]);
        }
        else{
            container = new Container();
            container.setAppDu(line[3]);
            container.setContainerId(line[0]);
            container.setStatuses(new ArrayList<>());
            this.currentContainers.put(line[0],container);
        }
        container.getStatuses().add(container.new CStatus(Integer.valueOf(line[2]),line[4],this.compactPartString(line,5,7),line[1]));
    }

    @Override
    public void bulkLines(){
        if(currentContainers.isEmpty()){
            return;
        }
        List<Container> insertContainers = new ArrayList<>();
        List<Container> updateContainers = new ArrayList<>();
        for(String key: currentContainers.keySet()){
            if(!containerIds.contains(key)){
                insertContainers.add(currentContainers.get(key));
                containerIds.add(key);
            }
            else{
                updateContainers.add(currentContainers.get(key));
            }
        }
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED,Container.class);
        for(Container c : insertContainers){
            ops.insert(c);
        }
        for(Container c: updateContainers){
            Update update = new Update();
            update.push("statuses").each(c.getStatuses().toArray());
            ops.updateOne(query(where("_id").is(c.getContainerId())),update);
        }
        ops.execute();

        insertContainers.clear();
        updateContainers.clear();
        currentContainers.clear();
    }
}
