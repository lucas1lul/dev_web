package br.edu.iff.ccc.bsi.webdev.controller.apirest;

import br.edu.iff.ccc.bsi.webdev.entities.*;
import br.edu.iff.ccc.bsi.webdev.service.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@OpenAPIDefinition(
    info = @Info(
        title = "API de Gerenciamento Completo",
        version = "1.0",
        description = "API para gerenciamento de todas as entidades do sistema"
    )
)
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Gerenciamento Completo", description = "API para gerenciar todas as entidades do sistema")
public class MainRestController {

    @Autowired private AdministradorService administradorService;
    @Autowired private OperadorService operadorService;
    @Autowired private PessoaService pessoaService;
    @Autowired private TarefaService tarefaService;
    @Autowired private TasksService tasksService;
    @Autowired private UsuarioService usuarioService;

    // ========== MÉTODOS TO MODEL ==========
    private EntityModel<Administrador> toModel(Administrador admin) {
        EntityModel<Administrador> model = EntityModel.of(admin);
        model.add(linkTo(methodOn(MainRestController.class).getAdministradorPorId(admin.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).getAdministradores()).withRel("administradores"));
        return model;
    }

    private EntityModel<Operador> toModel(Operador operador) {
        EntityModel<Operador> model = EntityModel.of(operador);
        model.add(linkTo(methodOn(MainRestController.class).getOperadorPorId(operador.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).getOperadores()).withRel("operadores"));
        return model;
    }

    private EntityModel<Pessoa> toModel(Pessoa pessoa) {
        EntityModel<Pessoa> model = EntityModel.of(pessoa);
        model.add(linkTo(methodOn(MainRestController.class).getPessoaPorId(pessoa.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).getPessoas()).withRel("pessoas"));
        return model;
    }

    private EntityModel<Tarefa> toModel(Tarefa tarefa) {
        EntityModel<Tarefa> model = EntityModel.of(tarefa);
        model.add(linkTo(methodOn(MainRestController.class).getTarefaPorId(tarefa.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).getTarefas()).withRel("tarefas"));
        return model;
    }

    private EntityModel<Tasks> toModel(Tasks task) {
        EntityModel<Tasks> model = EntityModel.of(task);
        model.add(linkTo(methodOn(MainRestController.class).getTaskPorId(task.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).getTasks()).withRel("tasks"));
        return model;
    }

    private EntityModel<Usuario> toModel(Usuario usuario) {
        EntityModel<Usuario> model = EntityModel.of(usuario);
        model.add(linkTo(methodOn(MainRestController.class).getUsuarioPorId(usuario.getId())).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).getUsuarios()).withRel("usuarios"));
        return model;
    }

    // ========== ADMINISTRADOR ==========
    @Operation(summary = "Listar administradores")
    @GetMapping("/administradores")
    public ResponseEntity<CollectionModel<EntityModel<Administrador>>> getAdministradores() {
        List<EntityModel<Administrador>> administradores = administradorService.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Administrador>> model = CollectionModel.of(administradores);
        model.add(linkTo(methodOn(MainRestController.class).getAdministradores()).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).criarAdministrador(null)).withRel("criar"));
        return ResponseEntity.ok(model);
    }

    @Operation(summary = "Buscar administrador por ID")
    @GetMapping("/administradores/{id}")
    public ResponseEntity<EntityModel<Administrador>> getAdministradorPorId(@PathVariable Long id) {
        return administradorService.findById(id)
            .map(this::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar administrador")
    @PostMapping("/administradores")
    public ResponseEntity<EntityModel<Administrador>> criarAdministrador(@RequestBody Administrador administrador) {
        Administrador saved = administradorService.save(administrador);
        return ResponseEntity
            .created(linkTo(methodOn(MainRestController.class).getAdministradorPorId(saved.getId())).toUri())
            .body(toModel(saved));
    }

    @Operation(summary = "Atualizar administrador")
    @PutMapping("/administradores/{id}")
    public ResponseEntity<EntityModel<Administrador>> atualizarAdministrador(
            @PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id);
        Administrador updated = administradorService.save(administrador);
        return ResponseEntity.ok(toModel(updated));
    }

    @Operation(summary = "Deletar administrador")
    @DeleteMapping("/administradores/{id}")
    public ResponseEntity<Void> deletarAdministrador(@PathVariable Long id) {
        if (administradorService.findById(id).isPresent()) {
            administradorService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ========== OPERADOR ==========
    @Operation(summary = "Listar operadores")
    @GetMapping("/operadores")
    public ResponseEntity<CollectionModel<EntityModel<Operador>>> getOperadores() {
        List<EntityModel<Operador>> operadores = operadorService.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Operador>> model = CollectionModel.of(operadores);
        model.add(linkTo(methodOn(MainRestController.class).getOperadores()).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).criarOperador(null)).withRel("criar"));
        return ResponseEntity.ok(model);
    }

    @Operation(summary = "Buscar operador por ID")
    @GetMapping("/operadores/{id}")
    public ResponseEntity<EntityModel<Operador>> getOperadorPorId(@PathVariable Long id) {  // Alterado para Long
        return operadorService.findById(id)
            .map(this::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar operador")
    @PostMapping("/operadores")
    public ResponseEntity<EntityModel<Operador>> criarOperador(@RequestBody Operador operador) {
        Operador saved = operadorService.save(operador);
        return ResponseEntity
            .created(linkTo(methodOn(MainRestController.class).getOperadorPorId(saved.getId())).toUri())
            .body(toModel(saved));
    }

    @Operation(summary = "Atualizar operador")
    @PutMapping("/operadores/{id}")
    public ResponseEntity<EntityModel<Operador>> atualizarOperador(
        @PathVariable Long id, @RequestBody Operador operador) {  // Alterado para Long
        operador.setId(id);
        Operador updated = operadorService.save(operador);
        return ResponseEntity.ok(toModel(updated));
    }

    @Operation(summary = "Deletar operador")
    @DeleteMapping("/operadores/{id}")
    public ResponseEntity<Void> deletarOperador(@PathVariable Long id) {  // Alterado para Long
        if (operadorService.findById(id).isPresent()) {
            operadorService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ========== PESSOA ==========
    @Operation(summary = "Listar pessoas")
    @GetMapping("/pessoas")
    public ResponseEntity<CollectionModel<EntityModel<Pessoa>>> getPessoas() {
        List<EntityModel<Pessoa>> pessoas = pessoaService.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Pessoa>> model = CollectionModel.of(pessoas);
        model.add(linkTo(methodOn(MainRestController.class).getPessoas()).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @Operation(summary = "Buscar pessoa por ID")
    @GetMapping("/pessoas/{id}")
    public ResponseEntity<EntityModel<Pessoa>> getPessoaPorId(@PathVariable Long id) {
        return pessoaService.findById(id)
            .map(this::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ========== TAREFA ==========
    @Operation(summary = "Listar tarefas")
    @GetMapping("/tarefas")
    public ResponseEntity<CollectionModel<EntityModel<Tarefa>>> getTarefas() {
        List<EntityModel<Tarefa>> tarefas = tarefaService.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Tarefa>> model = CollectionModel.of(tarefas);
        model.add(linkTo(methodOn(MainRestController.class).getTarefas()).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @Operation(summary = "Buscar tarefa por ID")
    @GetMapping("/tarefas/{id}")
    public ResponseEntity<EntityModel<Tarefa>> getTarefaPorId(@PathVariable Long id) {
        return tarefaService.findById(id)
            .map(this::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ========== TASKS ==========
    @Operation(summary = "Listar tasks")
    @GetMapping("/tasks")
    public ResponseEntity<CollectionModel<EntityModel<Tasks>>> getTasks() {
        List<EntityModel<Tasks>> tasks = tasksService.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Tasks>> model = CollectionModel.of(tasks);
        model.add(linkTo(methodOn(MainRestController.class).getTasks()).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @Operation(summary = "Buscar task por ID")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<EntityModel<Tasks>> getTaskPorId(@PathVariable Long id) {
        return tasksService.findById(id)
            .map(this::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ========== USUÁRIO ==========
    @Operation(summary = "Listar usuários")
    @GetMapping("/usuarios")
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> getUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Usuario>> model = CollectionModel.of(usuarios);
        model.add(linkTo(methodOn(MainRestController.class).getUsuarios()).withSelfRel());
        return ResponseEntity.ok(model);
    }

    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<EntityModel<Usuario>> getUsuarioPorId(@PathVariable Long id) {
        return usuarioService.findById(id)
            .map(this::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // ========== ENDPOINTS ESPECIAIS ==========
    @Operation(summary = "Gerenciar tarefas do operador")
    @PostMapping("/operador/gerenciarTarefas")
    public ResponseEntity<EntityModel<String>> gerenciarTarefas(
        @RequestParam Long idTarefa, @RequestParam String acao) {  // Alterado para Long
        EntityModel<String> model = EntityModel.of("Operador executando ação '" + acao + "' na tarefa " + idTarefa);
        model.add(linkTo(methodOn(MainRestController.class).gerenciarTarefas(idTarefa, acao)).withSelfRel());
        model.add(linkTo(methodOn(MainRestController.class).getTarefaPorId(idTarefa)).withRel("tarefa"));
        return ResponseEntity.ok(model);
    }
}