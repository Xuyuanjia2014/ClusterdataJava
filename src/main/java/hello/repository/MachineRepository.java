package hello.repository;

import hello.model.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MachineRepository extends MongoRepository<Machine, String> {
    List<Machine> findByMachineId(final String machine_id);
}
