package hello.demo.service;

import hello.demo.entity.Availability;
import hello.demo.entity.Person;
import hello.demo.entity.Product;
import hello.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getPeople() {
        List<Person> people = repository.findAll();


        return people;
    }
}
