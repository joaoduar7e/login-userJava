package controladores;


import controladores.util.JsfUtil;
import entidades.Telefone;
import entidades.Usuario;
import facade.UsuarioFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class UsuarioControle implements Serializable {
    private Usuario usuario = new Usuario();

    private Telefone telefone;

    @Inject
    transient private UsuarioFacade usuarioFacade;


    public void addTelefone() {
        Telefone tTemp = null;

        if (telefone.getNumero() == null) {
            telefone.setNumero((int) ' ');
        }
        String tel = telefone.getNumero().toString();

        if (telefone.getDdd().isEmpty()) {
            JsfUtil.adicionarMenssagemErro("O DDD não pode ser vazio!");
            return;
        } else if (telefone.getDdd().length() != 2) {
            JsfUtil.adicionarMenssagemErro("O DDD deve conter 2 caracteres!");
            return;
        } else if (tel.isEmpty()) {
            JsfUtil.adicionarMenssagemErro("O número não pode ser vazio!");
            return;
        } else if (telefone.getTipo().isEmpty()) {
            JsfUtil.adicionarMenssagemErro("O tipo de telefone não pode ser vazio!");
            return;
        } else {
            if (tTemp == null) {
                telefone.setUsuario(usuario);
                usuario.getTelefone().add(telefone);
            } else {
                tTemp.setDdd(telefone.getDdd());
                tTemp.setNumero(telefone.getNumero());
                tTemp.setTipo(telefone.getTipo());
            }
            telefone = new Telefone();
        }
    }

    public void removerTelefone(Telefone t) {
        usuario.getTelefone().remove(t);
    }

    public void novo() {
        usuario = new Usuario();
        telefone = new Telefone();
    }

    public void editar(Usuario usu) {
        usuario = usu;
    }

    public void excluir(Usuario usu) {
        usuarioFacade.remover(usu);
    }

    public void salvar() {
        try {
            if (usuario.getSenha().length() < 4) {
                JsfUtil.adicionarMenssagemErro("A senha deve conter no mínimo 4 caracteres!");
                return;
            } else {
                usuarioFacade.salvar(usuario);
                FacesContext.getCurrentInstance().getExternalContext().redirect("usuariolist.xhtml");
            }
        } catch (Exception ex) {
            JsfUtil.adicionarMenssagemErro("Já existe um usuário com esse NOME, digite um novo valor!");
        }
    }

    public List<Usuario> getListaUsuario() {
        return usuarioFacade.listaTodos();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
}
