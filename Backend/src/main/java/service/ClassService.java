package service;

import database.UniClassDatabase;
import database.UniClass;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    UniClassDatabase uniClassDatabase;

    public List<UniClass> getAllClasses() {
        return uniClassDatabase.findAll();
    }
}
