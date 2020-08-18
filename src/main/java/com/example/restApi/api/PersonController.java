package com.example.restApi.api;

import com.example.restApi.modal.Person;
import com.example.restApi.service.PersonService;
import com.sun.istack.NotNull;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private  final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public void addPerson(@Validated @NonNull @RequestBody Person person){
        personService.addPerson(person);
    }
    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonByID(id)
                .orElse( null);
    }
    @DeleteMapping(path = "{id}")
    public  void  deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);

    }
    @PutMapping(path = "{id}")
    public  void updatePerson(@PathVariable("id") UUID id , @Validated @NonNull @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);

    }
}
