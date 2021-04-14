package emt.labs.labEmt.web;

import emt.labs.labEmt.model.Book;
import emt.labs.labEmt.model.dto.CreateBookDto;
import emt.labs.labEmt.model.dto.EditBookDto;
import emt.labs.labEmt.model.dto.GetAllBooksDto;
import emt.labs.labEmt.model.dto.MarkBookAsTakenDto;
import emt.labs.labEmt.model.enums.Category;
import emt.labs.labEmt.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/librarian")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.bookService.createBook(createBookDto));
    }

    @PatchMapping("/librarian")
    public ResponseEntity<Book> editBook(@RequestBody EditBookDto editBookDto) {
        return ResponseEntity.ok(this.bookService.editBook(editBookDto));
    }

    @DeleteMapping("/librarian/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.deleteById(id));
    }

    @PatchMapping("/librarian/takeBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Book> takeBook(@RequestBody MarkBookAsTakenDto markBookAsTakenDto) {
        return ResponseEntity.ok(this.bookService.markBookAsTaken(markBookAsTakenDto));
    }

    @GetMapping("/user/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(this.bookService.findAllCategories());
    }

    @GetMapping("/user")
    public ResponseEntity<Page<GetAllBooksDto>> findAllBooks(@PageableDefault(sort = {"id"}, value = 5)Pageable pageable) {
        return ResponseEntity.ok(this.bookService.findAll(pageable));
    }
}
