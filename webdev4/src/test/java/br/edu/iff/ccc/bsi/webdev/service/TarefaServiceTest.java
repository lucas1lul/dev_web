package br.edu.iff.ccc.bsi.webdev.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.webdev.entities.Tarefa;
import br.edu.iff.ccc.bsi.webdev.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Busca por Id em TarefaService com sucesso.")
    void testFindById() {
        // Criando o objeto usando setters, pois não há um construtor com parâmetros
        Tarefa mockedTarefa = new Tarefa();
        mockedTarefa.setId(1L);
        mockedTarefa.setTitulo("Implementar API");
        mockedTarefa.setDescricao("Criar endpoints REST");

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(mockedTarefa));

        Tarefa result = tarefaService.findById(1L).orElse(null);

        assertNotNull(result);
        assertEquals("Implementar API", result.getTitulo());
        assertEquals("Criar endpoints REST", result.getDescricao());
        verify(tarefaRepository).findById(1L);
    }
}