package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.author.AuthorDto;
import com.cat.S5._2.bookstack.dtos.author.CreateAuthorDto;
import com.cat.S5._2.bookstack.dtos.author.UpdateAuthorDto;
import com.cat.S5._2.bookstack.entities.Author;
import com.cat.S5._2.bookstack.mappers.AuthorMapper;
import com.cat.S5._2.bookstack.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final static Logger logger = LoggerFactory.getLogger(AuthorService.class);

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDto create(CreateAuthorDto dto) {
        Author author = authorMapper.toEntity(dto);
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        Author saved = authorRepository.save(author);
        return authorMapper.toDto(saved);
    }

    public AuthorDto update(Long id, UpdateAuthorDto dto) {
        logger.debug("Attempting to update author with id: {}", id);
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            logger.warn("Author not found with id: {}", id);
            return null;
        }

        Author author = optionalAuthor.get();
        updateIfNotNull(dto.getFirstName(), author::setFirstName);
        updateIfNotNull(dto.getLastName(), author::setLastName);
        Author saved = authorRepository.save(author);
        logger.info("Author with id {} successfully updated", id);
        return authorMapper.toDto(saved);
    }

    private <T> void updateIfNotNull(T newValue, Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }
}
