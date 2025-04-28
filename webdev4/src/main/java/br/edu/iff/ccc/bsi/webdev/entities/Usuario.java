package br.edu.iff.ccc.bsi.webdev.entities;

import jakarta.persistence.*;

@Entity
public class Usuario extends Pessoa {

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario() {}

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
