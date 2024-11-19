package com.TaskManagement;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.TaskManagement.DTO.TasksDTO;
import com.TaskManagement.Exception.TaskNotFoundException;
import com.TaskManagement.Model.Tasks;
import com.TaskManagement.Repository.TaskRepo;
import com.TaskManagement.Repository.UserRepo;
import com.TaskManagement.Service.TaskService;


public class TaskServiceTest {

    @Mock
    private UserRepo userrepo;

    @Mock
    private TaskRepo taskrepo;

    @Mock
    private ModelMapper mapper;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private TaskService taskService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddATask() {
        TasksDTO tasksDTO = new TasksDTO();
        tasksDTO.setTitle("Test Task");

        Tasks task = new Tasks();
        task.setTitle("Test Task");

        when(mapper.map(tasksDTO, Tasks.class)).thenReturn(task);
        when(taskrepo.save(task)).thenReturn(task);
        when(mapper.map(task, TasksDTO.class)).thenReturn(tasksDTO);

        String result = taskService.addAtask(tasksDTO);

        assertEquals("Task Saved Successfully", result);
        verify(taskrepo, times(1)).save(task);
    }

    
    @Test
    void testFindById() throws TaskNotFoundException {
        Long taskId = 1L;
        Tasks task = new Tasks();
        task.setTitle("Sample Task");

        TasksDTO taskDTO = new TasksDTO();
        taskDTO.setTitle("Sample Task");

        when(taskrepo.findById(taskId)).thenReturn(Optional.of(task));
        when(mapper.map(task, TasksDTO.class)).thenReturn(taskDTO);

        TasksDTO result = taskService.findbyid(taskId);

        assertNotNull(result);
        assertEquals("Sample Task", result.getTitle());
    }


    
    @Test
    void testGetAllTasks() {
        Tasks task1 = new Tasks();
        Tasks task2 = new Tasks();
        List<Tasks> tasks = Stream.of(task1, task2).collect(Collectors.toList());

        TasksDTO taskDTO1 = new TasksDTO();
        TasksDTO taskDTO2 = new TasksDTO();

        when(taskrepo.findAll()).thenReturn(tasks);
        when(mapper.map(task1, TasksDTO.class)).thenReturn(taskDTO1);
        when(mapper.map(task2, TasksDTO.class)).thenReturn(taskDTO2);

        List<TasksDTO> result = taskService.getalltasks();

        assertEquals(2, result.size());
        verify(taskrepo, times(1)).findAll();
    }
    
    

    @Test
    void testFindByIdNotFound() {
        Long taskId = 1L;
        when(taskrepo.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.findbyid(taskId));
    }

    
    @Test
    void testUpdateATask() {
        Long taskId = 1L;
        Tasks existingTask = new Tasks();
        existingTask.setTitle("Old Title");

        TasksDTO updatedDTO = new TasksDTO();
        updatedDTO.setTitle("New Title");

        when(taskrepo.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskrepo.save(existingTask)).thenReturn(existingTask);
        when(mapper.map(existingTask, TasksDTO.class)).thenReturn(updatedDTO);

        String result = taskService.updateAtask(taskId, updatedDTO);

        assertEquals("Task updated successfully", result);
        assertEquals("New Title", existingTask.getTitle());
        verify(taskrepo, times(1)).save(existingTask);
    }

    
    @Test
    void testDeleteATask() {
        Long taskId = 1L;
        Tasks task = new Tasks();
        task.setTaskid(taskId);

        when(taskrepo.findById(taskId)).thenReturn(Optional.of(task));

        String result = taskService.deleteAtask(taskId);

        assertEquals("Task deleted successfully", result);
        verify(taskrepo, times(1)).delete(task);
    }

    @Test
    void testDeleteATaskNotFound() {
        Long taskId = 1L;
        when(taskrepo.findById(taskId)).thenReturn(Optional.empty());

        String result = taskService.deleteAtask(taskId);

        assertEquals("Task not found", result);
        verify(taskrepo, never()).delete(any(Tasks.class));
    }


    
}
