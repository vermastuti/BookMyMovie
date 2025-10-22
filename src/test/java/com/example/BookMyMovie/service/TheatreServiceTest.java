package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.TheatreDTO;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Theatre;
import com.example.BookMyMovie.repository.TheatreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreService theatreService;

    private Theatre theatre1;
    private Theatre theatre2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        theatre1 = new Theatre(100, "Delhi", "PVR");
        theatre1.setTheatreId(1);

        theatre2 = new Theatre(150, "Mumbai", "INOX");
        theatre2.setTheatreId(2);
    }

    @Test
    void testGetAllTheatres() {
        List<Theatre> theatreList = Arrays.asList(theatre1, theatre2);
        when(theatreRepository.findAll()).thenReturn(theatreList);

        List<Theatre> result = theatreService.getAll();

        assertEquals(2, result.size());
        verify(theatreRepository, times(1)).findAll();
    }

    @Test
    void testUpdateTheatre_NOTFound() {
        TheatreDTO updateddto = new TheatreDTO(200, "Delhi", "PVR Updated");
        Theatre updatedTheatre = new Theatre();

        assertThrows(IdDoesNotExistException.class, () -> theatreService.updateById(4,updateddto),"Theatre Not Found");
    }


}
