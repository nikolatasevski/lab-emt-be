package emt.labs.labEmt.service.impl;

import emt.labs.labEmt.model.Author;
import emt.labs.labEmt.model.Country;
import emt.labs.labEmt.model.dto.CreateAuthorDto;
import emt.labs.labEmt.model.exceptions.AuthorNotFoundException;
import emt.labs.labEmt.model.exceptions.CountryNotFoundException;
import emt.labs.labEmt.repository.AuthorRepository;
import emt.labs.labEmt.repository.CountryRepository;
import emt.labs.labEmt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    public final AuthorRepository authorRepository;
    public final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Author deleteById(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        this.authorRepository.delete(author);

        return author;
    }

    @Override
    public Author createAuthor(CreateAuthorDto createAuthorDto) {
        Author author = new Author();

        author.setName(createAuthorDto.getName());
        author.setSurname(createAuthorDto.getSurname());

        Country country = this.countryRepository.findById(createAuthorDto.getCountryId()).orElseThrow(() -> new CountryNotFoundException(createAuthorDto.getCountryId()));
        author.setCountry(country);

        this.authorRepository.save(author);

        return author;
    }

    @Override
    public List<Author> findAllAuthors() {
        return this.authorRepository.findAll();
    }
}
