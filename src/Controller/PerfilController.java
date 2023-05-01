package Controller;

import Entidades.Perfil;
import Entidades.Usuario;
import Persistencia.PerfilDAO;

public class PerfilController {

    private PerfilDAO daoPerfil;
    private Perfil perfil;
    private StringBuilder mensagemDeErro;
    private boolean camposValidos;

    private void setUp() {

        daoPerfil = new PerfilDAO();
        perfil = new Perfil();
        mensagemDeErro = new StringBuilder();
        camposValidos = false;
    }

    private boolean validarCampos(Perfil perfil) {
        camposValidos = true;

        if (perfil.getNome() == null
            || perfil.getNome().equals("")
            || perfil.getNome().isEmpty()) {
            mensagemDeErro.append("Nome não pode ser nulo! \n");
            camposValidos = false;
        }
        
        if (perfil.getDescricao() == null
                || perfil.getDescricao().equals("")
                || perfil.getDescricao().isEmpty()) {
            mensagemDeErro.append("Senha não pode ser nulo! \n");
            camposValidos = false;
        }
        return camposValidos;
    }

    public void alterarPerfil(Perfil perfil) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Perfil> getListaDePerfis() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
