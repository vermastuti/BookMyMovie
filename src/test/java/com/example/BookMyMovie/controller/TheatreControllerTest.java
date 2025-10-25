package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.dto.TheatreDTO;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Theatre;
import com.example.BookMyMovie.service.TheatreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TheatreControllerTest {

    @Mock
    private TheatreService theatreService;

    @InjectMocks
    private TheatreController theatreController;

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
        when(theatreService.getAll()).thenReturn(theatreList);

        theatreController.getAllTheatre();
        verify(theatreService, times(1)).getAll();
    }

    @Test
    void testUpdateTheatreById_Found() {
        TheatreDTO updateddto= new TheatreDTO(200, "Delhi", "PVR Updated");
        Theatre updatedTheatre=new Theatre();
        updatedTheatre.setCity(updateddto.getCity());
        updatedTheatre.setName(updateddto.getName());
        updatedTheatre.setSeats(updateddto.getSeats());
        updatedTheatre.setTheatreId(1);

        when(theatreService.updateById(1, updateddto)).thenReturn(updatedTheatre);

        theatreController.update(1, updateddto);
        verify(theatreService, times(1)).updateById(1, updateddto);
    }

    @Test
    void testUpdateTheatreById_NotFound() throws IdDoesNotExistException {
        TheatreDTO updateddto = new TheatreDTO(200, "Delhi", "PVR Updated");
        Theatre updatedTheatre=new Theatre();
        updatedTheatre.setCity(updateddto.getCity());
        updatedTheatre.setName(updateddto.getName());
        updatedTheatre.setSeats(updateddto.getSeats());
        updatedTheatre.setTheatreId(1);

        when(theatreService.add(updateddto)).thenReturn(updatedTheatre);

        theatreController.add(updateddto);
        verify(theatreService, times(1)).add(updateddto);
    }
}
