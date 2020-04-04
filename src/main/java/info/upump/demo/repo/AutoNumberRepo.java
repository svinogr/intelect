package info.upump.demo.repo;

import info.upump.demo.model.AutoNumber;
import org.springframework.data.repository.CrudRepository;

public interface AutoNumberRepo extends CrudRepository<AutoNumber, Long> {
    Iterable<AutoNumber> findByNumberContainingOrDescriptionContaining(String number, String description);
}
