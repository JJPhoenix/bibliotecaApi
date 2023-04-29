package pe.edu.pucp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.pucp.model.Libro;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByDisponible(boolean disponible);
    List<Libro> findByTituloContaining(String titulo);
    List<Libro> findByAutorContaining(String autor);
}
