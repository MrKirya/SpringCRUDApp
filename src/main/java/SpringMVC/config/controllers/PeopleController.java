package SpringMVC.config.controllers;

import SpringMVC.config.models.Person;
import SpringMVC.config.services.ItemService;
import SpringMVC.config.services.PeopleService;
import SpringMVC.config.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    private final ItemService itemService;

    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PeopleService peopleService, ItemService itemService, PersonValidator personValidator)
    {
        this.peopleService = peopleService;
        this.itemService = itemService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("people",peopleService.findAll());

        itemService.findByItemName("Dildo");
        itemService.findByOwner(peopleService.findAll().getFirst());

        peopleService.test();
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model)
    {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person person)
    {
        //model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult)
    {
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id)
    {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult, @PathVariable("id") int id)
    {
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        peopleService.delete(id);
        return "redirect:/people";
    }

}
