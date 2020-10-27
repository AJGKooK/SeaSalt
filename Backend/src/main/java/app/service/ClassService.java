package app.service;

import app.database.UniClassDatabase;
import app.database.UniClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
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
