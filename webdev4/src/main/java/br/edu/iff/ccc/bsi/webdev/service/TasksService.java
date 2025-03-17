package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Tasks;
import br.edu.iff.ccc.bsi.webdev.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public Optional<Tasks> findById(Long id) {
        return tasksRepository.findById(id);
    }

    public Tasks save(Tasks task) {
        return tasksRepository.save(task);
    }

    public void deleteById(Long id) {
        tasksRepository.deleteById(id);
    }
}
