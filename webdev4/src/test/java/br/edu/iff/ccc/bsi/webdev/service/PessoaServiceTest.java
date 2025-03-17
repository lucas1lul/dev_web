package br.edu.iff.ccc.bsi.webdev.service;

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

import br.edu.iff.ccc.bsi.webdev.entities.Pessoa;
import br.edu.iff.ccc.bsi.webdev.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Busca por Id em PessoaService com sucesso.")
    void testFindById() {
        Pessoa mockedPessoa = new Pessoa();
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(mockedPessoa));

        Pessoa result = pessoaService.findById(1L).orElse(null);

        assertNotNull(result);
        verify(pessoaRepository).findById(1L);
    }
}
