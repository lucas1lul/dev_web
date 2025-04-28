package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Operador;
import br.edu.iff.ccc.bsi.webdev.controller.apirest.MainRestController;
import br.edu.iff.ccc.bsi.webdev.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    public List<Operador> findAll() {
        return operadorRepository.findAll();
    }

    public Optional<Operador> findById(Long id) {
        return operadorRepository.findById(id);
    }

    public Operador save(Operador operador) {
        return operadorRepository.save(operador);
    }

    public void deleteById(Long id) {
        operadorRepository.deleteById(id);
    }

    // Método para suportar HATEOAS
    public EntityModel<Operador> toModel(Operador operador) {
        EntityModel<Operador> model = EntityModel.of(operador);
        model.add(linkTo(methodOn(MainRestController.class)
            .getOperadorPorId(operador.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class)
            .getOperadores()).withRel("operadores"));
        return model;
    }
}