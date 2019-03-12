package hello;

import hello.csv.readers.*;
import hello.util.MongoDBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		if(args == null || args.length == 0 || args[0] == null){
			log.error("ClusterdataJava does not receive any paramters, exit anyway.");
		}
		else{
			BasicReader.prefix = args[0];
			SpringApplication.run(Application.class, args);
		}
	}


	public void run(String... args) throws Exception {
		switch (args[1]){
			case "readMachineMeta":
				this.readMachineMeta(Integer.valueOf(args[2]));
				break;
			case "readMachineUsage":
				this.readMachineUsage(Integer.valueOf(args[2]));
				break;
			case "checkMachineUsage()":
				this.checkMachineUsage(args[2]);
				break;
			case "checkTaskMap":
				this.checkTaskMap();
				break;
			case "readTaskMeta":
				this.readTaskMeta(Integer.valueOf(args[2]));
				break;
			case "readInstanceUsage":
				this.readInstanceUsage(Integer.valueOf(args[2]));
				break;
			case "readContainerMeta":
				this.readContainerMeta(Integer.valueOf(args[2]));
				break;
			case "readContainerUsage":
				this.readContainerUsage(Integer.valueOf(args[2]));
				break;
			default:
				log.error("Wrong Operation:"+args[1] + " and ClusterdataJava will exit soon.");
				break;
		}
	}

	private void readMachineMeta(int bulkCount){
		//17592
		BasicReader br = ApplicationContextProvider.getBean(MachineMetaReader.class);
		br.readFile(BasicReader.machineMeta,bulkCount);
	}

	private void readMachineUsage(int bulkCount){
		BasicReader br = ApplicationContextProvider.getBean(MachineUsageReader.class);
		br.readFile(BasicReader.machineUsage,bulkCount);
	}

	private void checkMachineUsage(String mid){
		MongoDBHelper mh = ApplicationContextProvider.getBean(MongoDBHelper.class);
		//m_2123
		mh.findOneMachines(mid);
	}

	private void checkTaskMap(){
		MongoDBHelper mh = ApplicationContextProvider.getBean(MongoDBHelper.class);
		//addTaskMap
		mh.addTaskMap();
	}

	private void readTaskMeta(int bulkCount){
		BasicReader br = ApplicationContextProvider.getBean(TaskMetaReader.class);
		br.readFile(BasicReader.batchTask,bulkCount);
	}

	private void readInstanceUsage(int bulkCount){
		BasicReader br = ApplicationContextProvider.getBean(InstanceReader.class);
		br.readFile(BasicReader.batchInstance,bulkCount);
	}

	private void readContainerMeta(int bulkCount){
		BasicReader br = ApplicationContextProvider.getBean(ContainerMetaReader.class);
		br.readFile(BasicReader.containerMeta,bulkCount);
	}

	private void readContainerUsage(int bulkCount){
		BasicReader br = ApplicationContextProvider.getBean(ContainerUsageReader.class);
		br.readFile(BasicReader.containerUsage,bulkCount);
	}
}
