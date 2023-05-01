package Persistencia;

import Entidades.Perfil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PerfilDAO extends ConexaoComBancoDeDados implements InterfaceCrud<Integer, Perfil> {

    @Override
    public void salvar(Perfil perfil) {

        String sql = "INSERT INTO PERFIL("
                + "NOME,"
                + "DESCRICAO"
                + ") VALUES (?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, perfil.getNome());
            pstm.setString(2, perfil.getDescricao());
            pstm.execute();
            conexao.commit();                     
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Perfil buscarPorId(Integer id) {

        Perfil perfil = new Perfil();
        String sql = "SELECT * FROM PERFIL WHERE CODIGO = ?";

        try {
            conectar();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet list = pst.executeQuery();
            while (list.next()) {
                perfil.setCodigo(list.getInt("CODIGO"));
                perfil.setNome(list.getString("NOME"));
                perfil.setDescricao(list.getString("DESCRICAO"));
            }

            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return perfil;

    }

    @Override
    public void deletar(Perfil perfil) {

        String sql = "DELETE FROM PERFIL WHERE CODIGO = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, perfil.getCodigo());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Perfil update(Perfil perfil) {

        String sql = "UPDATE PERFIL SET "
                + "NOME = ?, "
                + "DESCRICAO = ? "
                + "WHERE CODIGO = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, perfil.getNome());
            pstm.setString(2, perfil.getDescricao());
            pstm.setInt(3, perfil.getCodigo());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return buscarPorId(perfil.getCodigo());

    }

    @Override
    public Collection<Perfil> listarTodos() {

        String sql = "SELECT * FROM PERFIL";
        List<Perfil> listaDePerfis = new ArrayList<Perfil>();

        try {
            conectar();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet lista = pst.executeQuery();
            while (lista.next()) {
                Perfil perfil = new Perfil();
                perfil.setCodigo(lista.getInt("CODIGO"));
                perfil.setNome(lista.getString("NOME"));
                perfil.setDescricao(lista.getString("DESCRICAO"));
                listaDePerfis.add(perfil);

            }

            desconetar();
        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return listaDePerfis;

    }
    
    
    
    
   
    

}
