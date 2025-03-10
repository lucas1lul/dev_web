package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String descricao;
    private String prioridade;
    private String categoria;
    private Date dataCriacao;
    private Date dataLimite;
    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_email")
    private Usuario usuario;

    public Tarefa() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarefa other = (Tarefa) obj;
        return Objects.equals(id, other.id);
    }
}
