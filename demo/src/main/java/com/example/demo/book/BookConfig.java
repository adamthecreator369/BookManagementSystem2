package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    /*  This class was used for development when settings were set to create-drop.
        Leaving this here for now in case you need it.
    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            Book book1 = new Book(
                    "3452-48975-3221-012",
                    "Clean Code",
                    "Uncle Bob",
                    "Checked Out"
            );
            Book book2 = new Book(
                    "3232-67943-1267-432",
                    "Data Structures and Algorithms",
                    "Programmers 'R' Us",
                    "In Stock"
            );
            repository.saveAll(
                    List.of(book1, book2)
            );
        };
    }

     */
}
