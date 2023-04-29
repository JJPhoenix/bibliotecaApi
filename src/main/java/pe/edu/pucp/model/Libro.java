package pe.edu.pucp.model;

import javax.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "disponible")
    private boolean disponible;
    @Column(name = "autor")
    private String autor;

    public Libro() {
    }

    public Libro(long id, String titulo, String descripcion, boolean disponible, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", disponible=" + disponible +
                ", autor='" + autor + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
