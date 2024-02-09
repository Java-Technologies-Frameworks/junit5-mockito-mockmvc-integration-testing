package com.jtf.junit5.mockito.service.impl;

import com.jtf.junit5.mockito.entity.Book;
import com.jtf.junit5.mockito.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book(101L, "Core Java", "James Gosling"),
                new Book(190L, "c programming", "Denis Ritche")));

        List<Book> books;
        books = bookService.getAllBooks();

        assertEquals(2, books.size());
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(123L)).thenReturn(Optional.of(new Book(100L, "The greats by", "Gosling")));
        Optional<Book> books = bookService.getBookById(123L);
        assertEquals("The greats by", books.get().getTitle());
    }

    @Test
    void testSaveBook() {
        Book newBook = new Book(null, "devops", "Mckdevops");

        when(bookRepository.save(newBook)).thenReturn(new Book(1L, "devops", "Mckdevops"));

        Book savedBook = bookService.saveBook(newBook);

        assertEquals(1L, savedBook.getId());
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
