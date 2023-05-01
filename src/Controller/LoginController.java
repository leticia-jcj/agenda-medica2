package Controller;

import Entidades.Usuario;
import Persistencia.UsuarioDAO;
import java.util.Collection;
import javax.swing.JOptionPane;

public class LoginController {

    private UsuarioDAO daoUsuario;
    private Usuario usuario;
    private Collection<Usuario> listaDeUsuario;
    private StringBuilder mensagemDeErro;
    private boolean camposValidos;

    private void setUp() {
        daoUsuario = new UsuarioDAO();
        usuario = new Usuario();
        mensagemDeErro = new StringBuilder();
        listaDeUsuario = daoUsuario.listarTodos();
        camposValidos = false;

    }

    public Usuario login(String username, String senha) {

        setUp(); 
        usuario.setUsername(username);
        usuario.setSenha(senha);

        if (validarCampos(usuario)) {
            for (Usuario usuarioBD : listaDeUsuario) {
                if (usuarioBD.getUsername().equals(username)
                        && usuarioBD.getSenha().equals(senha)) {
                    usuario = usuarioBD;
                    return usuario;
                }
            }
            
            
        }
        
        if (mensagemDeErro.length() >0 ){
            JOptionPane.showMessageDialog(null, mensagemDeErro.toString());
        }

        return null;

    }

    private boolean validarCampos(Usuario usuario) {
        camposValidos = true;

        if (usuario.getUsername() == null
                || usuario.getUsername().equals("")
                || usuario.getUsername().isEmpty()) {
            mensagemDeErro.append("Username não pode ser nulo! \n");
            camposValidos = false;
        }

        if (usuario.getSenha() == null
                || usuario.getSenha().equals("")
                || usuario.getSenha().isEmpty()) {
            mensagemDeErro.append("Senha não pode ser nulo! \n");
            camposValidos = false;
        }

        return camposValidos;
    }

}
