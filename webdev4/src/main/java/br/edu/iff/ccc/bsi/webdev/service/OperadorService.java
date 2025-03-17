package br.edu.iff.ccc.bsi.webdev.service;

import br.edu.iff.ccc.bsi.webdev.entities.Operador;
import br.edu.iff.ccc.bsi.webdev.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    public Optional<Operador> findById(String id) {
        return operadorRepository.findById(id);
    }

    public Operador save(Operador operador) {
        return operadorRepository.save(operador);
    }

    public void deleteById(String id) {
        operadorRepository.deleteById(id);
    }
}
