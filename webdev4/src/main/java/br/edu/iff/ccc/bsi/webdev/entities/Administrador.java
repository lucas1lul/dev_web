package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Administrador extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Definição do ID corretamente como Long

    public void gerenciarUsuarios() {
        // Implementação do gerenciamento de usuários
    }

    // Getters e Setters do ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}