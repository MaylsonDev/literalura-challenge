package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a WHERE a.birth_year <= :ano AND (a.death_year IS NULL OR a.death_year >= :ano)")
    List<Autor> findAutoresVivosNoAno(int ano);
}
