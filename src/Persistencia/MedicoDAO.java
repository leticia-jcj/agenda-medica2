package Persistencia;

import Entidades.Medico;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MedicoDAO extends ConexaoComBancoDeDados implements InterfaceCrud<Integer, Medico> {

    @Override
    public void salvar(Medico medico) {

        String sql = "INSERT INTO MEDICO("
                + "CRM,"
                + "NOME,"
                + "ESPECIALIDADE,"
                + "SEXO,"
                + "DATA_NASCIMENTO"
                + "SALARIO,"
                + ") VALUES (?,?,?,?,?,?);";

        try {
            conectar();

            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, medico.getCrm());
            pstm.setString(2, medico.getNome());
            pstm.setString(3, medico.getEspecialidade());
            pstm.setString(4, medico.getSexo());
            Date dataPadraoSql = new Date(medico.getDataNascimento().getTime());
            pstm.setDate(5, dataPadraoSql);
            pstm.setDouble(6, medico.getSalario());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Medico buscarPorId(Integer id) {

        Medico medico = new Medico();

        String sql = "SELECT * FROM MEDICO WHERE CRM = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                medico.setCrm(lista.getInt("CRM"));
                medico.setNome(lista.getString("NOME"));
                medico.setEspecialidade(lista.getString("ESPECIALIDADE"));
                medico.setSexo(lista.getString("SEXO"));
                medico.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));
                medico.setSalario(lista.getDouble("SALARIO"));

            }

            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return medico;

    }

    @Override
    public void deletar(Medico medico) {

        String sql = "DELETE FROM MEDICO WHERE CRM = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, medico.getCrm());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Medico update(Medico medico) {

        String sql = "UPDATE MEDICO SET "
                + "CRM,"
                + "NOME,"
                + "ESPECIALIDADE,"
                + "SEXO,"
                + "DATA_NASCIMENTO"
                + "SALARIO,"
                + ") VALUES (?,?,?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, medico.getCrm());
            pstm.setString(2, medico.getNome());
            pstm.setString(3, medico.getEspecialidade());
            pstm.setString(4, medico.getSexo());
            Date dataPadraoSql = new Date(medico.getDataNascimento().getTime());
            pstm.setDate(5, dataPadraoSql);
            pstm.setDouble(6, medico.getSalario());

            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return buscarPorId(medico.getCrm());

    }

    @Override
    public Collection<Medico> listarTodos() {

        List<Medico> listaDeMedicos = new ArrayList<Medico>();

        String sql = "SELECT * FROM MEDICO";

        try {
            conectar();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet lista = pst.executeQuery();
            while (lista.next()) {
                Medico medico = new Medico();
                medico.setCrm(lista.getInt("CRM"));
                medico.setNome(lista.getString("NOME"));
                medico.setEspecialidade(lista.getString("ESPECIALIDADE"));
                medico.setSexo(lista.getString("SEXO"));
                medico.setSalario(lista.getDouble("SALARIO"));
                medico.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));
                listaDeMedicos.add(medico);
            }

            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);
        }

        return listaDeMedicos;

    }

}
