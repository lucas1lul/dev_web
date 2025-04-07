package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Tarefa;
import br.edu.iff.ccc.bsi.webdev.controller.apirest.MainRestController;
import br.edu.iff.ccc.bsi.webdev.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa save(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void deleteById(Long id) {
        tarefaRepository.deleteById(id);
    }

    // Método para suportar HATEOAS
    public EntityModel<Tarefa> toModel(Tarefa tarefa) {
        EntityModel<Tarefa> model = EntityModel.of(tarefa);
        model.add(linkTo(methodOn(MainRestController.class)
            .getTarefaPorId(tarefa.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class)
            .getTarefas()).withRel("tarefas"));
        return model;
    }
}