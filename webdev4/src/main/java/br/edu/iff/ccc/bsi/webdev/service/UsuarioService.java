package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Usuario;
import br.edu.iff.ccc.bsi.webdev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método para suportar HATEOAS
    public EntityModel<Usuario> toModel(Usuario usuario) {
        EntityModel<Usuario> model = EntityModel.of(usuario);
        
        // Adiciona link para o próprio recurso
        model.add(linkTo(methodOn(br.edu.iff.ccc.bsi.webdev.controller.apirest.MainRestController.class)
            .getUsuarioPorId(usuario.getId())).withSelfRel());
        
        // Adiciona link para a coleção de usuários
        model.add(linkTo(methodOn(br.edu.iff.ccc.bsi.webdev.controller.apirest.MainRestController.class)
            .getUsuarios()).withRel("usuarios"));
        
        return model;
    }
}