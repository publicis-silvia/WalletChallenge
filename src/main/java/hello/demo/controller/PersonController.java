package hello.demo.controller;

import java.util.List;

import hello.demo.entity.Person;
import hello.demo.exceptions.PersonNotFoundException;
import hello.demo.repository.PersonRepository;
import hello.demo.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final PersonRepository repository;
    private final PersonService service;


    PersonController(PersonRepository repository, PersonService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/")
    public String list(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                       @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {
        model.addAttribute("employees", service.getPeople(pageNumber, size));

        return "index";
    }

    @GetMapping("/getPeople")
    List<Person> all() {
        return repository.findAll();
    }


    @PostMapping("/postPerson")
    Person addPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    // Single item

    @GetMapping("/getPerson/{id}")
    Person one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/putPerson/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/deletePerson")
    String deletePerson(@ModelAttribute(value="person") Person person, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                      @RequestParam(value = "size", required = false, defaultValue = "10") int size, Model model) {

        repository.deleteById(person.getId());
        model.addAttribute("people", service.getPeople(pageNumber, size));


        return "redirect:index";
    }
}
