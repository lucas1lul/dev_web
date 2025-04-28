package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Pessoa;
import br.edu.iff.ccc.bsi.webdev.controller.apirest.MainRestController;
import br.edu.iff.ccc.bsi.webdev.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }

    // Método para suportar HATEOAS
    public EntityModel<Pessoa> toModel(Pessoa pessoa) {
        EntityModel<Pessoa> model = EntityModel.of(pessoa);
        model.add(linkTo(methodOn(MainRestController.class)
            .getPessoaPorId(pessoa.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class)
            .getPessoas()).withRel("pessoas"));
        return model;
    }
}