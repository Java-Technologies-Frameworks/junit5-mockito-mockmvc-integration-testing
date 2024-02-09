package com.jtf.junit5.mockito;

import com.jtf.junit5.mockito.repository.BookRepository;
import com.jtf.junit5.mockito.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Junit5MockitoExampleApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAllBooks() {

    }
}
