package hello.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hello.demo.entity.Person;
import hello.demo.repository.PersonRepository;

@Service
public class PersonService implements DataAccessService<Person> {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<Person> getAll() {
		List<Person> people = repository.findAll();
		return people;
	}

	@Override
	public Person getById(Long personId) {
		Optional<Person> person = repository.findById(personId);
		return person.get();
	}

	@Override
	public long add(Person newPerson) {
		Person person = repository.save(newPerson);
		return person.getId();
	}

	@Override
	public void deleteById(Long personId) {
		repository.deleteById(personId);
	}

	@Override
	public void update(long personId, Person person) {
		repository.deleteById(personId);
		repository.save(person);
	}
}
