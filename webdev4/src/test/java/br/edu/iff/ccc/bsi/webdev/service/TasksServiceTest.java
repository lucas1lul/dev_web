package br.edu.iff.ccc.bsi.webdev.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.iff.ccc.bsi.webdev.entities.Tasks;
import br.edu.iff.ccc.bsi.webdev.repository.TasksRepository;

class TasksServiceTest {

    @Mock
    private TasksRepository tasksRepository;

    @InjectMocks
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Tasks task = new Tasks();
        task.setId(1L);
        
        when(tasksRepository.findById(1L)).thenReturn(Optional.of(task));

        Tasks result = tasksService.findById(1L).orElse(null);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(tasksRepository).findById(1L);
    }
}
