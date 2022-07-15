package hello.demo.service;

import hello.demo.entity.Person;
import hello.demo.frontend.Page;
import hello.demo.frontend.Paged;
import hello.demo.frontend.Paging;
import hello.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Paged<Person> getPeople(int pageNumber, int size) {

        List<Person> people =  repository.findAll();

        List<Person> paged = people.stream()
                .skip(pageNumber)
                .limit(size)
                .collect(Collectors.toList());

        int totalPages = people.size() / size;
        return new Paged<>(new Page<>(paged, totalPages), Paging.of(totalPages, pageNumber, size));

    }

}
