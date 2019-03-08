package hello.repository;

import hello.model.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MachineRepository extends MongoRepository<Machine, String> {
    List<Machine> findByMachine_id(final String machine_id);
}
