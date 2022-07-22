package hello.demo.service;

import java.util.List;
import java.util.Optional;

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
		Optional<Person> person = repository.findById(personId);
		return person.get();
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
