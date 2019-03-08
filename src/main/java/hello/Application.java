package hello;

import hello.csv.readers.BasicReader;
import hello.csv.readers.MachineMetaReader;
import hello.csv.readers.MachineUsageReader;
import hello.util.MongoDBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	public void run(String... args) throws Exception {
		//this.readMachineMeta();
		//this.readMachineUsage();
		this.checkMachineUsage();
	}

	private void readMachineMeta(){
		BasicReader br = ApplicationContextProvider.getBean(MachineMetaReader.class);
		br.readFile(BasicReader.machineMeta,17592);
	}

	private void readMachineUsage(){
		BasicReader br = ApplicationContextProvider.getBean(MachineUsageReader.class);
		br.readFile(BasicReader.machineUsage,1000000);
	}

	private void checkMachineUsage(){
		MongoDBHelper mh = ApplicationContextProvider.getBean(MongoDBHelper.class);
		//m_2123
		mh.checkOneMachine("m_2003");
	}

}
