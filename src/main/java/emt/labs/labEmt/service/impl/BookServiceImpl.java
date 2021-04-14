package emt.labs.labEmt.service.impl;

import emt.labs.labEmt.model.Author;
import emt.labs.labEmt.model.Book;
import emt.labs.labEmt.model.dto.GetAllBooksDto;
import emt.labs.labEmt.model.dto.MarkBookAsTakenDto;
import emt.labs.labEmt.model.dto.CreateBookDto;
import emt.labs.labEmt.model.dto.EditBookDto;
import emt.labs.labEmt.model.enums.Category;
import emt.labs.labEmt.model.exceptions.AuthorNotFoundException;
import emt.labs.labEmt.model.exceptions.BookNotFoundException;
import emt.labs.labEmt.model.exceptions.NoCopiesLeftException;
import emt.labs.labEmt.repository.AuthorRepository;
import emt.labs.labEmt.repository.BookRepository;
import emt.labs.labEmt.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book deleteById(Long id) {
        Book book = findById(id);

        this.bookRepository.delete(book);

        return book;
    }

    @Override
    public Book createBook(CreateBookDto createBookDto) {
        Book book = new Book();

        return getBook(book, createBookDto.getName(), createBookDto.getCategory(), createBookDto.getAvailableCopies(), createBookDto.getAuthorId());
    }

    @Override
    public Book editBook(EditBookDto editBookDto) {
        Book book = findById(editBookDto.getId());

        return getBook(book, editBookDto.getName(), editBookDto.getCategory(), editBookDto.getAvailableCopies(), editBookDto.getAuthorId());
    }

    @Override
    public List<Category> findAllCategories() {
        return List.of(Category.values());
    }

    @Override
    public Book markBookAsTaken(MarkBookAsTakenDto markBookAsTakenDto) {
        Book book = findById(markBookAsTakenDto.getId());

        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        } else throw new NoCopiesLeftException(markBookAsTakenDto.getId());

        return this.bookRepository.save(book);
    }

    @Override
    public Page<GetAllBooksDto> findAll(Pageable pageable) {
        ModelMapper mapper = new ModelMapper();

        return this.bookRepository.findAll(pageable)
                .map(book -> mapper.map(book, GetAllBooksDto.class));
    }

    private Book getBook(Book book, String name, Category category, Integer availableCopies, Long authorId) {
        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        this.bookRepository.save(book);

        return book;
    }
}
