package service;

import database.UniClassDatabase;
import database.UniClass;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    UniClassDatabase uniClassDatabase;

    public List<UniClass> getAllClasses() {
        return uniClassDatabase.findAll();
    }

    public Optional<UniClass> getClassById(Integer id) {
        return uniClassDatabase.findById(id);
    }

    public void addClass(UniClass uniClass) {
        uniClassDatabase.save(uniClass);
    }
}
