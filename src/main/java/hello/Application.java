package hello;

import hello.csv.readers.BasicReader;
import hello.csv.readers.MachineMetaReader;
import hello.csv.readers.MachineUsageReader;
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
		this.checkTaskMap();
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
}
