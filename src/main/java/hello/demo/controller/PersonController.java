package hello.demo.controller;

import hello.demo.entity.Person;
import hello.demo.repository.PersonRepository;
import hello.demo.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

    private final PersonRepository repository;
    private final PersonService service;


    PersonController(PersonRepository repository, PersonService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/getPeople")
    public String list(Model model) {
        model.addAttribute("people", service.getPeople());

        return "people";
    }

    @GetMapping("/redirectAddPersonPage")
    public String list2(Model model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }


    @PostMapping("/postPerson")
    public String addPerson(@ModelAttribute Person newPerson, Model model) {
        repository.save(newPerson);
        model.addAttribute("people", service.getPeople());

        return "redirect:/getPeople";
    }

    @GetMapping("/getPerson")
    public ModelAndView getPerson(@RequestParam Long personId) {
        ModelAndView mav = null;
        Person person = repository.findById(personId).get();
        if(person.getWallet() != null){
            mav = new ModelAndView("showPersonWallet");
            mav.addObject("wallet", person.getWallet());
        }else{
            mav = new ModelAndView("showPerson");
        }

        mav.addObject("person", person);
        return mav;
    }

    @GetMapping("/putPerson")
    public String replacePerson(@ModelAttribute Person updatedPerson, Model model) {

        repository.findById(updatedPerson.getId())
                .map(person -> {
                    person.setName(updatedPerson.getName());
                    person.setBirthday(updatedPerson.getBirthday());
                    return repository.save(person);
                });

        model.addAttribute("people", service.getPeople());
        return "redirect:/getPeople";
    }
    @GetMapping("/showUpdateFormPerson")
    public ModelAndView showUpdateFormPerson(@RequestParam Long personId) {
        ModelAndView mav = new ModelAndView("updatePerson");
        Person person = repository.findById(personId).get();
        mav.addObject("person", person);
        return mav;
    }

    @GetMapping("/deletePerson")
    public String deletePerson(@RequestParam Long personId, Model model) {

        repository.deleteById(personId);
        model.addAttribute("people", service.getPeople());


        return "redirect:/getPeople";
    }
}
