package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // <- Move a estratégia para a raiz da hierarquia
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String email;
    
    private String nome;
    private String senha;

    public Pessoa() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pessoa other = (Pessoa) obj;
        return Objects.equals(email, other.email);
    }
}
