package hello;

import hello.csv.readers.BasicReader;
import hello.csv.readers.MachineMetaReader;
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
		System.out.println("ApplicationContextProvider.getApplicationContext():"+ApplicationContextProvider.getApplicationContext());
		this.readMachineMeta();
	}

	private void readMachineMeta(){
		BasicReader br = ApplicationContextProvider.getBean(MachineMetaReader.class);
		br.readFile(BasicReader.machineMeta,17592);
	}

}
