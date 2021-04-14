package emt.labs.labEmt.web;


import emt.labs.labEmt.model.Author;
import emt.labs.labEmt.model.dto.CreateAuthorDto;
import emt.labs.labEmt.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/author", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    public final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll(){
        return ResponseEntity.ok(this.authorService.findAllAuthors());
    }

    @PostMapping
    public Author createAuthor(@RequestBody CreateAuthorDto createAuthorDto){
        return this.authorService.createAuthor(createAuthorDto);
    }

    @DeleteMapping("/{id}")
    public Author deleteAuthor(@PathVariable Long id){
        return this.authorService.deleteById(id);
    }
}
