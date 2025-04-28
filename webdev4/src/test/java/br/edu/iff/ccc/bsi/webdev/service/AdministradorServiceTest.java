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

import br.edu.iff.ccc.bsi.webdev.entities.Administrador;
import br.edu.iff.ccc.bsi.webdev.repository.AdministradorRepository;

@ExtendWith(MockitoExtension.class)
public class AdministradorServiceTest {

    @InjectMocks
    private AdministradorService administradorService;

    @Mock
    private AdministradorRepository administradorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Busca por Id em AdministradorService com sucesso.")
    void testFindById() {
        Administrador mockedAdmin = new Administrador();
        when(administradorRepository.findById(1L)).thenReturn(Optional.of(mockedAdmin));

        Administrador result = administradorService.findById(1L).orElse(null);

        assertNotNull(result);
        verify(administradorRepository).findById(1L);
    }
}
