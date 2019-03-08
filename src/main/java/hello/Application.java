package hello;

import hello.csv.readers.BasicReader;
import hello.csv.readers.MachineMetaReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.readMachineMeta();
	}

	private void readMachineMeta(){
		BasicReader br = new MachineMetaReader();
		br.readFile(BasicReader.machineMeta,10000000);
	}

}
