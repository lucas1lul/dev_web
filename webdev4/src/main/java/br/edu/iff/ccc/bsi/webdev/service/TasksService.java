package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Tasks;
import br.edu.iff.ccc.bsi.webdev.controller.apirest.MainRestController;
import br.edu.iff.ccc.bsi.webdev.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public List<Tasks> findAll() {
        return tasksRepository.findAll();
    }

    public Optional<Tasks> findById(Long id) {
        return tasksRepository.findById(id);
    }

    public Tasks save(Tasks task) {
        return tasksRepository.save(task);
    }

    public void deleteById(Long id) {
        tasksRepository.deleteById(id);
    }

    // Método para suportar HATEOAS
    public EntityModel<Tasks> toModel(Tasks task) {
        EntityModel<Tasks> model = EntityModel.of(task);
        model.add(linkTo(methodOn(MainRestController.class)
            .getTaskPorId(task.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class)
            .getTasks()).withRel("tasks"));
        return model;
    }
}