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
        // 1. Cria um operador de teste
        Operador operador = new Operador();
        operador.setId(1L); // ID agora é Long
        
        // 2. Configura o mock para retornar o operador quando buscar pelo ID 1L
        when(operadorRepository.findById(1L)).thenReturn(Optional.of(operador));

        // 3. Chama o método a ser testado
        Optional<Operador> result = operadorService.findById(1L);

        // 4. Verificações
        assertTrue(result.isPresent()); // Verifica se o Optional contém um valor
        assertEquals(operador, result.get()); // Verifica se o operador retornado é o mesmo
        verify(operadorRepository).findById(1L); // Verifica se o método do repositório foi chamado
    }
}