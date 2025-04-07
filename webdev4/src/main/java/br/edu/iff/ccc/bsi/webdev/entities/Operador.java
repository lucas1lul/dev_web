package br.edu.iff.ccc.bsi.webdev.entities;

import jakarta.persistence.Entity;

@Entity
public class Operador extends Pessoa {
    
    private static final long serialVersionUID = 1L;

    // Construtor padrão necessário para JPA
    public Operador() {
        super();
    }

    // Construtor com parâmetros
    public Operador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void gerenciarTarefas() {
        // Implementação do gerenciamento de tarefas
    }
}