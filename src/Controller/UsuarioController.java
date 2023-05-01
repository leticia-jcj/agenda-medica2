package Controller;

import Entidades.Perfil;
import Entidades.Usuario;
import Persistencia.PerfilDAO;
import Persistencia.UsuarioDAO;
import java.util.Collection;
import lombok.Getter;


public class UsuarioController {
    
    private UsuarioDAO daoUsuario;
    private PerfilDAO daoPerfil;
    @Getter
    private Collection<Perfil> listaDePerfil;
    @Getter
    private Collection<Usuario> listaDeUsuario;
    
    public UsuarioController(){
        setUp();
    }
    
    private void setUp(){
        
        daoUsuario = new UsuarioDAO();
        daoPerfil = new PerfilDAO();
        listaDePerfil = daoPerfil.listarTodos();
        listaDeUsuario = daoUsuario.listarTodos();
        
    }

    public void alterarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Perfil> listarTodosPerfis() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Usuario> listarTodosuUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void cadastrarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
