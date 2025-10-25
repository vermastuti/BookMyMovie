package com.example.BookMyMovie.service;

import com.example.BookMyMovie.repository.ShowRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ShowServiceTest {
    @Mock
    ShowRepository repo;

    @InjectMocks
    ShowService service;


}