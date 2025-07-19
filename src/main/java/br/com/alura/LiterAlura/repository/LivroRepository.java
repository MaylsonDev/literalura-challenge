package br.com.alura.LiterAlura.repository;
import br.com.alura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    @Query(value = "SELECT * FROM livros l WHERE :idioma = ANY (l.languages)", nativeQuery = true)
    List<Livro> findByIdioma(@Param("idioma") String idioma);
}
