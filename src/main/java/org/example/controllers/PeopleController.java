package org.example.controllers;

import org.example.dao.PersonDao;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PersonDao personDao;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String Show(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDao.show(id));
        return "/people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "/people/new";
    }
    @PostMapping()
    public String Create(Model model, @ModelAttribute("person") Person person){
        personDao.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String Edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDao.show(id));
        return "/people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDao.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable("id") int id){
        personDao.delete(id);
        return "redirect:/people";
    }
}
