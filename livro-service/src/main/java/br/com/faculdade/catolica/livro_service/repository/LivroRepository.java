package br.com.faculdade.catolica.livro_service.repository;

import br.com.faculdade.catolica.livro_service.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
