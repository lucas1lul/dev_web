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

import br.edu.iff.ccc.bsi.webdev.entities.Usuario;
import br.edu.iff.ccc.bsi.webdev.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Busca por Id em UsuarioService com sucesso.")
    void testFindById() {
        Usuario mockedUsuario = new Usuario();
        mockedUsuario.setId(1L);  

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockedUsuario));

        Usuario result = usuarioService.findById(1L).orElse(null);

        assertNotNull(result);
        verify(usuarioRepository).findById(1L);
    }
}
