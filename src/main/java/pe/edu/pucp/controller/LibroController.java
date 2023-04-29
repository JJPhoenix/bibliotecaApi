package pe.edu.pucp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.model.Libro;
import pe.edu.pucp.repository.LibroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/biblioteca")
public class LibroController {

    @Autowired
    LibroRepository libroRepository;

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> getLibros(@RequestParam(required = false) String titulo) {
        try {
            List<Libro> libros = new ArrayList<Libro>();

            if (titulo == null)
                libroRepository.findAll().forEach(libros::add);
            else
                libroRepository.findByTituloContaining(titulo).forEach(libros::add);

            if (libros.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> getLibrosById(@PathVariable("id") long id) {
        Optional<Libro> tutorialData = libroRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/libros/autor/{autor}")
    public ResponseEntity<List<Libro>> getLibrosByAutor(@PathVariable("autor") String autor) {
        try {
            List<Libro> libros = new ArrayList<Libro>();

            if (autor == null)
                libroRepository.findAll().forEach(libros::add);
            else
                libroRepository.findByAutorContaining(autor).forEach(libros::add);

            if (libros.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/libros")
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        try {
            Libro _libro = libroRepository
                    .save(new Libro(libro.getId(),libro.getTitulo(), libro.getDescripcion(), false,libro.getAutor()));
            return new ResponseEntity<>(_libro, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable("id") long id, @RequestBody Libro libro) {
        Optional<Libro> libroData = libroRepository.findById(id);

        if (libroData.isPresent()) {
            Libro _libro = libroData.get();
            _libro.setTitulo(libro.getTitulo());
            _libro.setDescripcion(libro.getDescripcion());
            _libro.setAutor(libro.getAutor());
            _libro.setDisponible(libro.isDisponible());
            return new ResponseEntity<>(libroRepository.save(_libro), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<HttpStatus> deleteLibro(@PathVariable("id") long id) {
        try {
            libroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/libros")
    public ResponseEntity<HttpStatus> deleteLibros() {
        try {
            libroRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/libros/disponibles")
    public ResponseEntity<List<Libro>> libroDisponibles() {
        try {
            List<Libro> tutorials = libroRepository.findByDisponible(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
