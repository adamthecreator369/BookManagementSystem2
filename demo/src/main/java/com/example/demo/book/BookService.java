package com.example.demo.book;

// Business Layer

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long bookId) {
         Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new IllegalStateException("Book with id " + bookId + " does not exist.");
        }
        return bookRepository.findById(bookId);
    }

    public void addNewBook(Book book) {
        validateISBN(book.getIsbn());
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateBookStatus(book.getStatus());
        book.setStatus(book.getStatus().toUpperCase());
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(book.getIsbn());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException(
                    "ISBN " + book.getIsbn() + " is already in use."
            );
        }
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Long bookId, String isbn, String title, String author, String status) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException(
                "Book with id " + bookId + " does not exist."
        ));

        if (valueHasBeenChanged(isbn, book.getIsbn())) {
            validateISBN(isbn);
            book.setIsbn(isbn);
        }

        if (valueHasBeenChanged(title, book.getTitle())) {
            validateTitle(title);
            book.setTitle(title);
        }

        if (valueHasBeenChanged(author, book.getAuthor())) {
            validateAuthor(author);
            book.setAuthor(author);
        }

        if (valueHasBeenChanged(status, book.getStatus())) {
            validateBookStatus(status);
            book.setStatus(status.toUpperCase());
        }
    }

    private boolean valueHasBeenChanged(String newVal, String originalVal) {
        return newVal != null && newVal.length() > 0 && !Objects.equals(newVal, originalVal);
    }

    private void validateBookStatus(String status) {
        if (status.trim().length() == 0) {
            throw new IllegalArgumentException("Status field cannot be blank.");
        }
        if (!(status.toLowerCase().equals("in stock")
                || status.toLowerCase().equals("checked out"))) {
            throw new IllegalArgumentException("\"" + status + "\"" + " is not a valid book status.");
        }
    }

    private void validateAuthor(String author) {
        if (author.trim().length() == 0) {
            throw new IllegalArgumentException("Author field cannot be blank.");
        }
    }

    private void validateTitle(String title) {
        if (title.trim().length() == 0) {
            throw new IllegalArgumentException("Title field cannot be blank.");
        }
    }

    private void validateISBN(String isbn) {
        if (isbn.trim().length() == 0) {
            throw new IllegalArgumentException("ISBN field cannot be blank.");
        }

        String isbnRegex = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]" +
                "{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}" +
                "[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

        Pattern pattern = Pattern.compile(isbnRegex);
        Matcher matcher = pattern.matcher(isbn);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("ISBN entered does not follow correct ISBN-10 or ISBN-13 format.");
        }

        Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("ISBN is already in use.");
        }
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("Book with Id " + bookId + " does not exist.");
        }
        bookRepository.deleteById(bookId);
    }

    public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.bookRepository.findAll(pageable);
    }
}
