package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Telefone implements Serializable, ClassePai {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Usuario usuario;

    private String ddd;
    private Integer numero;
    private String tipo;


    // Gets e Sets
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(id, telefone.id) && Objects.equals(usuario, telefone.usuario) && Objects.equals(ddd, telefone.ddd) && Objects.equals(numero, telefone.numero) && Objects.equals(tipo, telefone.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, ddd, numero, tipo);
    }

    @Override
    public String toString() {
        return "entidades.Telefone[ id=" + id + " ]";
    }


}
    