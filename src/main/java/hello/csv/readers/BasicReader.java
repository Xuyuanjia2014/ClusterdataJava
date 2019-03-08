package hello.csv.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class BasicReader {
    public static String batchInstance ="E:\\benchmark\\alibaba_clusterdata2018\\alibaba_clusterdata_v2018\\batch_instance.csv";
    public static String batchTask = "E:\\benchmark\\alibaba_clusterdata2018\\alibaba_clusterdata_v2018\\batch_task.csv";
    public static String containerMeta = "E:\\benchmark\\alibaba_clusterdata2018\\alibaba_clusterdata_v2018\\container_meta.csv";
    public static String containerUsage = "E:\\benchmark\\alibaba_clusterdata2018\\alibaba_clusterdata_v2018\\container_usage.csv";
    public static String machineMeta = "E:\\benchmark\\alibaba_clusterdata2018\\alibaba_clusterdata_v2018\\machine_meta.csv";
    public static String machineUsage = "E:\\benchmark\\alibaba_clusterdata2018\\alibaba_clusterdata_v2018\\machine_usage.csv";

    // for Machines:
    public static String machineId = "machine_id";
    public static String timeStamp = "time_stamp";
    public static String disater1 = "disaster_level_1";
    public static String disater2 = "disaster_level_2";
    public static String cpu = "cpu_num";
    public static String mem = "mem_size";

    //map in array key is timestamp:
    public static String statuses = "statuses";
    public static String usages = "usages";

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

//    public class Machine {
//        public String ;
//        public int ;
//        public String ;
//        public String disaster_level_2;
//        public int ;
//        public int mem_size;
//        public String[] ;
//    }
    protected TreeMap<String,Object> machines = new TreeMap<>();
    protected Map<String,Map[]> machineStatuses = new TreeMap<>();
    protected Map<String,Map[]> machineSUsage = new TreeMap<>();

    protected TreeMap<String,Object> tasks = new TreeMap<>();
    protected TreeMap<String,Object> instances = new TreeMap<>();

    protected TreeMap<String,Object> applications = new TreeMap<>();
    protected TreeMap<String,Object> containers = new TreeMap<>();


    public void operateEachLine(String line){

    }

    public void bulkLines(){

    }

    public void readLine(String fileName,long bulkCount){
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            long size = 0;
            while ((str = bf.readLine()) != null) {
                operateEachLine(str);
                size++;
                if(size%bulkCount == 0){
                    bulkLines();
                }
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
