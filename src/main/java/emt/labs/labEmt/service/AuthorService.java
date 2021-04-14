package emt.labs.labEmt.service;

import emt.labs.labEmt.model.Author;
import emt.labs.labEmt.model.dto.CreateAuthorDto;

import java.util.List;

public interface AuthorService {

    Author deleteById(Long id);

    Author createAuthor(CreateAuthorDto createAuthorDto);

    List<Author> findAllAuthors();
}
