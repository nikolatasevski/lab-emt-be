package emt.labs.labEmt.service;


import emt.labs.labEmt.model.Book;
import emt.labs.labEmt.model.dto.CreateBookDto;
import emt.labs.labEmt.model.dto.EditBookDto;
import emt.labs.labEmt.model.dto.GetAllBooksDto;
import emt.labs.labEmt.model.dto.MarkBookAsTakenDto;
import emt.labs.labEmt.model.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book findById(Long id);

    Book deleteById(Long id);

    Book createBook(CreateBookDto createBookDto);

    Book editBook(EditBookDto editBookDto);

    List<Category> findAllCategories();

    Book markBookAsTaken(MarkBookAsTakenDto markBookAsTakenDto);

    Page<GetAllBooksDto> findAll(Pageable pageable);
}
