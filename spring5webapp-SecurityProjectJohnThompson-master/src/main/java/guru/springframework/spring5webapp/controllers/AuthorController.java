package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.databaseConfiguration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;
     @RequestMapping("/")  public String defaultPortal(){ return "authors/Iist"; }
    private final List<Author> authorList = new ArrayList<>();  

    public AuthorController(AuthorRepository authorRepository) {
         databaseConfiguration dc= new databaseConfiguration();
        this.authorRepository = authorRepository;
    }

   

    @GetMapping("/authors/new")
    public String readdata(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        return "authors/Iist";
    }

    @GetMapping("/authors/new/{nombre}/{apellido}")
    public String createAuthor(Model model, @PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido) {
        
        String name = StringUtils.isEmpty(nombre) ? "Andres" : nombre;
        String lastname = StringUtils.isEmpty(apellido) ? "Miranda" : apellido;
        model.addAttribute("author", new Author(name, lastname));
        Author author = new Author(name, lastname);
        authorList.add(author);
        model.addAttribute("authors", authorList);
        return "authors/Iist";
    }
}