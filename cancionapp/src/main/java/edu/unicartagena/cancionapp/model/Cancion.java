package edu.unicartagena.cancionapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cancion")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ritmo;
    private Integer duracion; // en segundos
    private String album;
    private Integer posicionEnAlbum;
    private String banda;
    private String interprete;
    private String autor;
    private LocalDate fechaLanzamiento;

    public Cancion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRitmo() { return ritmo; }
    public void setRitmo(String ritmo) { this.ritmo = ritmo; }
    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }
    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }
    public Integer getPosicionEnAlbum() { return posicionEnAlbum; }
    public void setPosicionEnAlbum(Integer posicionEnAlbum) { this.posicionEnAlbum = posicionEnAlbum; }
    public String getBanda() { return banda; }
    public void setBanda(String banda) { this.banda = banda; }
    public String getInterprete() { return interprete; }
    public void setInterprete(String interprete) { this.interprete = interprete; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public LocalDate getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }
}