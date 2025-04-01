package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Administrador;
import br.edu.iff.ccc.bsi.webdev.controller.apirest.MainRestController;
import br.edu.iff.ccc.bsi.webdev.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> findAll() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> findById(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador save(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public void deleteById(Long id) {
        administradorRepository.deleteById(id);
    }

    // Método para suportar HATEOAS
    public EntityModel<Administrador> toModel(Administrador administrador) {
        EntityModel<Administrador> model = EntityModel.of(administrador);
        model.add(linkTo(methodOn(MainRestController.class)
            .getAdministradorPorId(administrador.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class)
            .getAdministradores()).withRel("administradores"));
        return model;
    }
}