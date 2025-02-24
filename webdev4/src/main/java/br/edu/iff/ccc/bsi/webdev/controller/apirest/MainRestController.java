package br.edu.iff.ccc.bsi.webdev.controller.apirest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OpenAPIDefinition(
    info = @Info(
        title = "API de Gerenciamento de Usuários e Tarefas",
        version = "1.0",
        description = "API para cadastro e gerenciamento de usuários e tarefas no sistema."
    )
)
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Gerenciamento de Usuários e Tarefas", description = "API para gerenciar usuários e tarefas do sistema")
public class MainRestController {
    
    @Operation(summary = "Listar usuários", description = "Retorna uma lista de usuários cadastrados", 
               responses = {@ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")})
    @GetMapping(path = "/usuarios")
    public ResponseEntity<List<Map<String, String>>> getUsuarios() {
        List<Map<String, String>> lista = new ArrayList<>();
        
        Map<String, String> item1 = new HashMap<>();
        item1.put("idUsuario", "1234");
        item1.put("nome", "Ronaldo");
        item1.put("senha", "****");
        Map<String, String> item2 = new HashMap<>();
        item2.put("idUsuario", "2345");
        item2.put("nome", "Maria");
        item2.put("senha", "****");
        lista.add(item1);
        lista.add(item2);
        
        return ResponseEntity.ok(lista);
    }
    
    @Operation(summary = "Obter usuário por ID", description = "Retorna os dados de um usuário pelo ID",
               responses = {@ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                              content = @Content(schema = @Schema(implementation = String.class)))})
    @GetMapping(path = "/usuarios/{idUsuario}")
    public ResponseEntity<String> getUsuarioPorId(@PathVariable("idUsuario") int idUsuario) {
        return ResponseEntity.ok().header("Content-Type", "application/json").body("Usuário -> " + idUsuario);
    }
    
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário no sistema",
               responses = {@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")})
    @PostMapping(path = "/usuarios")
    public String criarUsuario(@RequestParam("nome") String nome, @RequestParam("email") String email, @RequestParam("senha") String senha) {
        return "Usuário criado: " + nome + " - Email: " + email + " - Senha: " + senha;
    }
    
    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa no sistema",
               responses = {@ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso")})
    @PostMapping(path = "/tarefas")
    public String criarTarefa(@RequestParam("titulo") String titulo, @RequestParam("descricao") String descricao, @RequestParam("prioridade") String prioridade, @RequestParam("categoria") String categoria, @RequestParam("dataCriacao") String dataCriacao, @RequestParam("dataLimite") String dataLimite, @RequestParam("status") String status) {
        return "Tarefa criada: " + titulo + " - Descrição: " + descricao + " - Prioridade: " + prioridade + " - Categoria: " + categoria + " - Data Criação: " + dataCriacao + " - Data Limite: " + dataLimite + " - Status: " + status;
    }
    
    @Operation(summary = "Gerenciar tarefas do operador", description = "Permite que um operador gerencie suas próprias tarefas")
    @PostMapping(path = "/operador/gerenciarTarefas")
    public String gerenciarTarefas(@RequestParam("idTarefa") String idTarefa, @RequestParam("acao") String acao) {
        return "Operador executando ação '" + acao + "' na tarefa " + idTarefa;
    }
    
    @Operation(summary = "Gerenciar usuários do administrador", description = "Permite que um administrador gerencie outros usuários e suas tarefas")
    @PostMapping(path = "/administrador/gerenciarUsuarios")
    public String gerenciarUsuarios(@RequestParam("idUsuario") String idUsuario, @RequestParam("acao") String acao) {
        return "Administrador executando ação '" + acao + "' no usuário " + idUsuario;
    }
}
