package Persistencia;

import Entidades.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends ConexaoComBancoDeDados implements InterfaceCrud<Integer, Usuario> {

    PerfilDAO daoPerfil = new PerfilDAO();

    @Override
    public void salvar(Usuario usuario) {

        String sql = "INSERT INTO USUARIO("
                + "CPF,"
                + "NOME,"
                + "USERNAME,"
                + "SENHA,"
                + "CODIGO_PERFIL,"
                + "TELEFONE,"
                + "MATRICULA,"
                + "DATA_NASCIMENTO"
                + ") VALUES (?,?,?,?,?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuario.getCpf());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getUsername());
            pstm.setString(4, usuario.getSenha());
            pstm.setInt(5, usuario.getPerfil().getCodigo());
            pstm.setString(6, usuario.getTelefone());
            pstm.setString(7, usuario.getMatricula());
            Date dataPadraoSql = new Date(usuario.getDataNascimento().getTime());
            pstm.setDate(8, dataPadraoSql);
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Usuario buscarPorId(Integer id) {

        Usuario usuario = new Usuario();

        String sql = "SELECT * FROM USUARIO WHERE ID = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                usuario.setId(lista.getInt("ID"));
                usuario.setCpf(lista.getString("CPF"));
                usuario.setNome(lista.getString("NOME"));
                usuario.setUsername(lista.getString("USERNAME"));
                usuario.setSenha(lista.getString("SENHA"));
                usuario.setPerfil(daoPerfil.buscarPorId(lista.getInt("CODIGO_PERFIL")));
                usuario.setTelefone(lista.getString("TELEFONE"));
                usuario.setMatricula(lista.getString("MATRICULA"));
                usuario.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));

            }

            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return usuario;

    }

    @Override
    public void deletar(Usuario usuario) {
        
        String sql = "DELETE FROM USUARIO WHERE ID = ?;";
        
        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, usuario.getId());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Usuario update(Usuario usuario) {

        String sql = "UPDATE USUARIO SET "
                + " CPF=?,"
                + " NOME=?, "
                + " USERNAME=?, "
                + " SENHA=?, "
                + " CODIGO_PERFIL=?,"
                + " TELEFONE=?, "
                + " DATA_NASCIMENTO=? WHERE ID=?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuario.getCpf());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getUsername());
            pstm.setString(4, usuario.getSenha());
            pstm.setInt(5, usuario.getPerfil().getCodigo());
            pstm.setString(6, usuario.getTelefone());
            Date dataPadraoSql = new Date(usuario.getDataNascimento().getTime());
            pstm.setDate(7, dataPadraoSql);
            pstm.setInt(8, usuario.getId());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }
        
        return buscarPorId(usuario.getId());

    }

    @Override
    public List<Usuario> listarTodos() {

        List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

        String sql = "SELECT * FROM USUARIO";

        try {
            conectar();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet lista = pst.executeQuery();
            while (lista.next()) {
                Usuario usuario = new Usuario();
                usuario.setCpf(lista.getString("CPF"));
                usuario.setNome(lista.getString("NOME"));
                usuario.setUsername(lista.getString("USERNAME"));
                usuario.setSenha(lista.getString("SENHA"));
                usuario.setPerfil(daoPerfil.buscarPorId(lista.getInt("CODIGO_PERFIL")));
                usuario.setTelefone(lista.getString("TELEFONE"));
                usuario.setMatricula(lista.getString("MATRICULA"));
                usuario.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));
                listaDeUsuarios.add(usuario);
            }

            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);
        }

        return listaDeUsuarios;

    }

}
