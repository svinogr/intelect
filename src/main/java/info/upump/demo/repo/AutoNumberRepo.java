package info.upump.demo.repo;

import info.upump.demo.model.AutoNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface AutoNumberRepo extends CrudRepository<AutoNumber, Long> {
    Page<AutoNumber>findByNumberContainingOrDescriptionContaining(String number, String description, Pageable pageable);

    Page<AutoNumber> findAll(Pageable pageble);
}
