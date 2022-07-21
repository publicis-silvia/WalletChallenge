package hello.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hello.demo.entity.Person;
import hello.demo.repository.PersonRepository;

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

	public Person getPersonById(Long personId) {
		Person person = repository.getReferenceById(personId);
		return person;
	}

	public long addPerson(Person newPerson) {
		Person person = repository.save(newPerson);
		return person.getId();
	}

	public void deletePersonById(Long personId) {
		repository.deleteById(personId);
	}

	public void updatePerson(long personId, Person person) {
		repository.deleteById(personId);
		repository.save(person);
	}
}
