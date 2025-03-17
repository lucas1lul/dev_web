package br.edu.iff.ccc.bsi.webdev.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.iff.ccc.bsi.webdev.entities.Operador;
import br.edu.iff.ccc.bsi.webdev.repository.OperadorRepository;

class OperadorServiceTest {

    @Mock
    private OperadorRepository operadorRepository;

    @InjectMocks
    private OperadorService operadorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Operador operador = new Operador();
        String operadorId = "123";
        
        when(operadorRepository.findById(operadorId)).thenReturn(Optional.of(operador));

        Operador result = operadorService.findById(operadorId).orElse(null);

        assertNotNull(result);
        verify(operadorRepository).findById(operadorId);
    }
}
