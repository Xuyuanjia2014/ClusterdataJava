package hello.csv.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
public class BasicReader {
    public static String prefix = "E:\\benchmark\\alibaba_clusterdata2018\\alibaba_clusterdata_v2018\\";
    public static String batchInstance =prefix+"batch_instance.csv";
    public static String batchTask = prefix+"batch_task.csv";
    public static String containerMeta = prefix+"container_meta.csv";
    public static String containerUsage = prefix+"container_usage.csv";
    public static String machineMeta = prefix+"machine_meta.csv";
    public static String machineUsage = prefix+"machine_usage.csv";

    // for Machines:

    // for Containers:
    public static String containerId = "container_id";
    //machineId
    public static String containerApp = "app_du";
    public static String cpuRequest = "cpu_request";
    public static String cpuLimit = "cpu_limit";
    //mem size
    //statuses
    //usages

    //for tasks:
    public static String taskName = "task_name";
    public static String instanceNumber = "inst_num";
    public static String taskType = "task_type";
    public static String jobName = "job_name";
    //statuses

    //status as the key:
//    public static String start_time = "start_time";
//    public static String end_time = "end_time";
//    public static String plan_cpu = "plan_cpu";
//    public static String plan_mem = "plan_mem";


    //for instances:
    public static String inst_name = "inst_name";
    //taskName
    //jobName
    //taskType
    //statuses status as the key

    @Autowired
    protected MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    protected Map<String,Map[]> machineSUsage = new TreeMap<>();

    protected TreeMap<String,Object> tasks = new TreeMap<>();
    protected TreeMap<String,Object> instances = new TreeMap<>();

    protected TreeMap<String,Object> applications = new TreeMap<>();
    protected TreeMap<String,Object> containers = new TreeMap<>();

    private static Logger log = LoggerFactory.getLogger(BasicReader.class);

    public static int lLengh = 8;


    public void operateEachLine(String[] line){

    }

    public void bulkLines(){

    }

    public void init(){
        log.info("Init is nothing to do.");
    }

    protected String compactPartString(String[] line, int s, int e){
        StringBuffer sb = new StringBuffer("");
        try {
            for(int i= s;i<e;i++){
                sb.append(line[i]+",");
            }
            sb.append(line[e]);
        }catch (Exception ex){
            StringBuffer sb2 = new StringBuffer();
            for(String temp: line){
                sb2.append(temp+",");
            }
            System.out.println(sb2.toString());
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public void readLine(String fileName,long bulkCount){
        long size = 0;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            while ((str = bf.readLine()) != null) {
                String[] lines = str.split(",");
                if(lines.length !=lLengh){
                    continue;
                }
                operateEachLine(lines);
                size++;
                if(size%bulkCount == 0){
                    bulkLines();
                    log.info("Lines' size: "+size);
                }
            }
            bf.close();
            fr.close();
            log.info("To do the rest:");
            bulkLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("lines' total size:"+size);
    }

    public void readFile(String fileName,long bulkCount){
        init();
        readLine(fileName,bulkCount);
    }
}
