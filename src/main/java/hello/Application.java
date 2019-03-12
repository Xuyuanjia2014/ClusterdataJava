package hello;

import hello.csv.readers.*;
import hello.repository.MachineRepository;
import hello.util.MongoDBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	public void run(String... args) throws Exception {
		//this.readMachineMeta();
		//this.readMachineUsage();
		//this.checkMachineUsage();
//		this.checkTaskMap();
//		this.readTaskMeta();
//		this.readInstanceUsage();
		this.readContainerMeta();
//		this.readContainerUsage();
	}

	private void readMachineMeta(){
		BasicReader br = ApplicationContextProvider.getBean(MachineMetaReader.class);
		br.readFile(BasicReader.machineMeta,17592);
	}

	private void readMachineUsage(){
		BasicReader br = ApplicationContextProvider.getBean(MachineUsageReader.class);
		br.readFile(BasicReader.machineUsage,5000000);
	}

	private void checkMachineUsage(){
		MongoDBHelper mh = ApplicationContextProvider.getBean(MongoDBHelper.class);
		//m_2123
		mh.findOneMachines("m_2151");
	}

	private void checkTaskMap(){
		MongoDBHelper mh = ApplicationContextProvider.getBean(MongoDBHelper.class);
		//addTaskMap
		mh.addTaskMap();
	}

	private void readTaskMeta(){
		BasicReader br = ApplicationContextProvider.getBean(TaskMetaReader.class);
		br.readFile(BasicReader.batchTask,1000000);
	}

	private void readInstanceUsage(){
		BasicReader br = ApplicationContextProvider.getBean(InstanceReader.class);
		br.readFile(BasicReader.batchInstance,5000000);
	}

	private void readContainerMeta(){
		BasicReader br = ApplicationContextProvider.getBean(ContainerMetaReader.class);
		br.readFile(BasicReader.containerMeta,100000);
	}

	private void readContainerUsage(){
		BasicReader br = ApplicationContextProvider.getBean(ContainerUsageReader.class);
		br.readFile(BasicReader.containerUsage,5000000);
	}
}
