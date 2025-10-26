package com.example.BookMyMovie.controller;
import com.example.BookMyMovie.dto.TheatreDTO;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.Theatre;
import com.example.BookMyMovie.service.TheatreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/theatre")
@Validated
@CrossOrigin(
        exposedHeaders = "Content-Range"
)
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @ExceptionHandler(DuplicateIdFoundException.class)
    @PostMapping("/admin/add")
    public ResponseEntity<?> add(@Valid @RequestBody TheatreDTO theatredto) {
        try {
            System.out.println("Inside controller");
            Theatre addedtheatre = theatreService.add(theatredto);
            return new ResponseEntity<>(addedtheatre, HttpStatus.OK);
        } catch (DuplicateIdFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTheatre() {
        return new ResponseEntity<>(theatreService.getAll(), HttpStatus.OK);
    }


    @ExceptionHandler(IdDoesNotExistException.class)
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@Valid @RequestBody TheatreDTO theatredto) {
        try {
            System.out.println("Inside controller");
            Theatre updatedtheatre = theatreService.updateById(id,theatredto);
            return new ResponseEntity<>(updatedtheatre, HttpStatus.OK);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }



}