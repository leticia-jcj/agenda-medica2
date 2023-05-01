package Persistencia;

import Entidades.Plano;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ivanoliveira
 */

public class PlanoDAO extends ConexaoComBancoDeDados implements InterfaceCrud<Integer, Plano> {

    @Override
    public void salvar(Plano plano) {

        String sql = "INSERT INTO PLANO("
                + "CODIGO_PLANO,"
                + "OPERADORA,"
                + "TELEFONE,"
                + "ENDERECO,"
                + "REGISTRO_ANS"
                + ") VALUES (?,?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, plano.getCodigo());
            pstm.setString(2, plano.getOperadora());
            pstm.setString(3, plano.getTelefone());
            pstm.setString(4, plano.getEndereco());
            pstm.setString(5, plano.getRegistroAns());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Plano buscarPorId(Integer id) {

        Plano plano = new Plano();

        String sql = "SELECT * FROM PLANO WHERE ID = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                plano.setId(lista.getInt("ID"));
                plano.setCodigo(lista.getString("CODIGO_PLANO"));
                plano.setOperadora(lista.getString("OPERADORA"));
                plano.setTelefone(lista.getString("TELEFONE"));
                plano.setEndereco(lista.getString("ENDERECO"));
                plano.setRegistroAns(lista.getString("REGISTRO_ANS"));

            }

            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return plano;

    }

    @Override
    public void deletar(Plano plano) {

        String sql = "DELETE FROM PLANO WHERE ID = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, plano.getId());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Plano update(Plano plano) {

        String sql = "UPDATE PLANO SET "
                + " CODIGO_PLANO = ?, "
                + " OPERADORA = ?, "
                + " TELEFONE = ?, "
                + " ENDERECO = ?, "
                + " REGISTRO_ANS = ?"
                + " WHERE ID = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, plano.getCodigo());
            pstm.setString(2, plano.getOperadora());
            pstm.setString(3, plano.getTelefone());
            pstm.setString(4, plano.getEndereco());
            pstm.setString(5, plano.getRegistroAns());
            pstm.setInt(6, plano.getId());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return buscarPorId(plano.getId());

    }

    @Override
    public List<Plano> listarTodos() {

        String sql = "SELECT * FROM PLANO";
        List<Plano> listaDePlanos = new ArrayList<Plano>();

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                Plano plano = new Plano();
                plano.setId(lista.getInt("ID"));
                plano.setCodigo(lista.getString("CODIGO_PLANO"));
                plano.setOperadora(lista.getString("OPERADORA"));
                plano.setTelefone(lista.getString("TELEFONE"));
                plano.setEndereco(lista.getString("ENDERECO"));
                plano.setRegistroAns(lista.getString("REGISTRO_ANS"));
                listaDePlanos.add(plano);
            }

            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);
        }

        return listaDePlanos;

    }

    private void desconectar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
