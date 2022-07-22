package hello.demo.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hello.demo.entity.Person;
import hello.demo.service.PersonService;

@RestController
public class PersonResource {
	private final PersonService service;

	PersonResource(PersonService service) {
		this.service = service;
	}

	@RequestMapping("api/person/v1/get")
	public List<Person> getAllPeople(Model model) {
		List<Person> people = service.getAll();
		return people;
	}

	@RequestMapping("api/person/v1/get/{personId}")
	public Person getPersonById(@PathVariable Long personId) {
		Person person = service.getById(personId);

		if (person == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return person;
	}

	@RequestMapping(value = "api/person/v1/create", method = RequestMethod.POST)
	public ResponseEntity<Object> addPerson(@RequestBody Person newPerson) {

		long personId = service.add(newPerson);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{personId}").buildAndExpand(personId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "api/person/v1/delete/{personId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePersonById(@PathVariable Long personId) {
		service.deleteById(personId);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "api/person/v1/update/{personId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updatePerson(@PathVariable long personId, @RequestBody Person person) {

		service.update(personId, person);

		return ResponseEntity.noContent().build();
	}

}
