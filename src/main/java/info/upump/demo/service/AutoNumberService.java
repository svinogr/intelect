package info.upump.demo.service;

import info.upump.demo.model.AutoNumber;
import info.upump.demo.repo.AutoNumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoNumberService {
    @Autowired
    private AutoNumberRepo autoNumberRepo;

    public void addNumber(AutoNumber autoNumber) {
        autoNumberRepo.save(autoNumber);
    }

    public Iterable<AutoNumber> findAllNumbers() {
        return autoNumberRepo.findAll();
    }

    public void deleteNumber(AutoNumber autoNumber) {
        autoNumberRepo.delete(autoNumber);
    }

    public Iterable<AutoNumber> filter(String filter) {
        filter.trim();
        return autoNumberRepo.findByNumberContainingOrDescriptionContaining(filter, filter);
    }
}
