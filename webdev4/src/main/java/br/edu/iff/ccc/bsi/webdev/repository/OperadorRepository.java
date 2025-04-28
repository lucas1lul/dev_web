package br.edu.iff.ccc.bsi.webdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.iff.ccc.bsi.webdev.entities.Operador;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {
    // Métodos customizados podem ser adicionados aqui
    // Exemplo: List<Operador> findByNomeContaining(String nome);
}