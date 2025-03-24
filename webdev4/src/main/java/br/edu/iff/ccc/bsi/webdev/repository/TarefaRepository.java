package br.edu.iff.ccc.bsi.webdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.iff.ccc.bsi.webdev.entities.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
