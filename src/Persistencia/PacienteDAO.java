package Persistencia;

import Entidades.Paciente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PacienteDAO extends ConexaoComBancoDeDados implements InterfaceCrud<Integer, Paciente> {

    @Override
    public void salvar(Paciente paciente) {

        String sql = "INSERT INTO PACIENTE("
                + "ID,"
                + "CPF"
                + "NOME,"
                + "TELEFONE,"
                + "DATA_NASCIMENTO"
                + "ENDERECO,"
                + "SEXO,"
                + ") VALUES (?,?,?,?,?,?,?);";

        try {
            conectar();

            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, paciente.getId());
            pstm.setString(2, paciente.getCpf());
            pstm.setString(3, paciente.getNome());
            pstm.setString(4, paciente.getTelefone());
            Date dataPadraoSql = new Date(paciente.getDataNascimento().getTime());
            pstm.setDate(5, dataPadraoSql);
            pstm.setString(6, paciente.getEndereco());
            pstm.setString(7, paciente.getSexo());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Paciente buscarPorId(Integer id) {
        Paciente paciente = new Paciente();

        String sql = "SELECT * FROM PACIENTE WHERE ID = ?";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                paciente.setId(lista.getInt("ID"));
                paciente.setCpf(lista.getString("CPF"));
                paciente.setNome(lista.getString("NOME"));
                paciente.setTelefone(lista.getString("TELEFONE"));
                paciente.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));
                paciente.setEndereco(lista.getString("ENDERECO"));
                paciente.setSexo(lista.getString("SEXO").charAt(0));

            }

            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return paciente;
    }

    @Override
    public void deletar(Paciente paciente) {

        String sql = "DELETE FROM PACIENTE WHERE ID = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, paciente.getId());
            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Paciente update(Paciente paciente) {

        String sql = "UPDATE PACIENTE SET "
                + "ID,"
                + "CPF"
                + "NOME,"
                + "TELEFONE,"
                + "DATA_NASCIMENTO"
                + "ENDERECO,"
                + "SEXO,"
                + ") VALUES (?,?,?,?,?,?,?);";
        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, paciente.getId());
            pstm.setString(2, paciente.getCpf());
            pstm.setString(3, paciente.getNome());
            pstm.setString(4, paciente.getTelefone());
            Date dataPadraoSql = new Date(paciente.getDataNascimento().getTime());
            pstm.setDate(5, dataPadraoSql);
            pstm.setString(6, paciente.getEndereco());
            pstm.setString(7, paciente.getSexo());

            pstm.execute();
            conexao.commit();
            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        return buscarPorId(paciente.getId());

    }

    @Override
    public Collection<Paciente> listarTodos() {
        List<Paciente> listaDePacientes = new ArrayList<Paciente>();

        String sql = "SELECT * FROM PACIENTE";

        try {
            conectar();
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet lista = pst.executeQuery();
            while (lista.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(lista.getInt("ID"));
                paciente.setCpf(lista.getString("CPF"));
                paciente.setNome(lista.getString("NOME"));
                paciente.setTelefone(lista.getString("TELEFONE"));
                paciente.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));
                paciente.setEndereco(lista.getString("ENDERECO"));
                paciente.setSexo(lista.getString("SEXO").charAt(0));
                listaDePacientes.add(paciente);
            }

            desconetar();

        } catch (SQLException ex) {

            System.out.println(ex);
        }

        return listaDePacientes;

    }

}
